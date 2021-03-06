/******************************************************************************
 * Copyright (c) 2000-2019 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.designer.AST.TTCN3.templates;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.titan.common.logging.ErrorReporter;
import org.eclipse.titan.designer.AST.ASTVisitor;
import org.eclipse.titan.designer.AST.Assignment;
import org.eclipse.titan.designer.AST.IReferenceChain;
import org.eclipse.titan.designer.AST.IType;
import org.eclipse.titan.designer.AST.IValue;
import org.eclipse.titan.designer.AST.Reference;
import org.eclipse.titan.designer.AST.IType.Type_type;
import org.eclipse.titan.designer.AST.Scope;
import org.eclipse.titan.designer.AST.Value;
import org.eclipse.titan.designer.AST.TTCN3.Expected_Value_type;
import org.eclipse.titan.designer.AST.TTCN3.definitions.Def_Template;
import org.eclipse.titan.designer.AST.TTCN3.templates.PatternString.PatternType;
import org.eclipse.titan.designer.AST.TTCN3.types.CharString_Type;
import org.eclipse.titan.designer.AST.TTCN3.types.UniversalCharstring_Type;
import org.eclipse.titan.designer.AST.TTCN3.values.Charstring_Value;
import org.eclipse.titan.designer.AST.TTCN3.values.Referenced_Value;
import org.eclipse.titan.designer.AST.TTCN3.values.expressions.ExpressionStruct;
import org.eclipse.titan.designer.compiler.JavaGenData;
import org.eclipse.titan.designer.parsers.CompilationTimeStamp;
import org.eclipse.titan.designer.parsers.ttcn3parser.TTCN3ReferenceAnalyzer;

//FIXME implement as soon as charstring pattern templates become handled
/**
 * Represents a template that holds a charstring pattern.
 *
 * @author Kristof Szabados
 * */
public final class UnivCharString_Pattern_Template extends TTCN3Template {

	private final PatternString patternstring;
	
	private static final Pattern PATTERN_DYNAMIC_REFERENCE = Pattern.compile( "(.*?)\\{([A-Za-z].*?[A-Za-z0-9_]*)\\}(.*)" );

	public UnivCharString_Pattern_Template() {
		patternstring = new PatternString(PatternType.UNIVCHARSTRING_PATTERN);
		patternstring.setFullNameParent(this);
	}

	public UnivCharString_Pattern_Template(final PatternString ps) {
		patternstring = ps;

		if (patternstring != null) {
			patternstring.setFullNameParent(this);
		}
	}

	public PatternString getPatternstring() {
		return patternstring;
	}

	@Override
	/** {@inheritDoc} */
	public Template_type getTemplatetype() {
		return Template_type.USTR_PATTERN;
	}

	@Override
	/** {@inheritDoc} */
	public String getTemplateTypeName() {
		if (isErroneous) {
			return "erroneous universal character string pattern";
		}

		return "universal character string pattern";
	}

	@Override
	/** {@inheritDoc} */
	public String createStringRepresentation() {
		final StringBuilder builder = new StringBuilder("pattern \"");
		builder.append(patternstring.getFullString());
		builder.append('"');

		if (lengthRestriction != null) {
			builder.append(lengthRestriction.createStringRepresentation());
		}
		if (isIfpresent) {
			builder.append("ifpresent");
		}

		return builder.toString();
	}

	@Override
	/** {@inheritDoc} */
	public void setMyScope(final Scope scope) {
		super.setMyScope(scope);

		if (patternstring != null) {
			patternstring.setMyScope(scope);
		}
	}

	@Override
	/** {@inheritDoc} */
	public void setCodeSection(final CodeSectionType codeSection) {
		super.setCodeSection(codeSection);
		patternstring.setCodeSection(codeSection);
		if (lengthRestriction != null) {
			lengthRestriction.setCodeSection(codeSection);
		}
	}

	public boolean patternContainsAnyornoneSymbol() {
		return true;
	}

	public int getMinLengthOfPattern() {
		// TODO maybe we can say something more precise
		return 0;
	}

	@Override
	/** {@inheritDoc} */
	public Type_type getExpressionReturntype(final CompilationTimeStamp timestamp, final Expected_Value_type expectedValue) {
		if (getIsErroneous(timestamp)) {
			return Type_type.TYPE_UNDEFINED;
		}

		return Type_type.TYPE_UCHARSTRING;
	}

	@Override
	/** {@inheritDoc} */
	public boolean checkExpressionSelfReferenceTemplate(final CompilationTimeStamp timestamp, final Assignment lhs) {
		//FIXME implement once patterns are supported

		return false;
	}

	@Override
	/** {@inheritDoc} */
	public void checkSpecificValue(final CompilationTimeStamp timestamp, final boolean allowOmit) {
		getLocation().reportSemanticError("A specific value expected instead of a universal charstring pattern");
	}

	@Override
	/** {@inheritDoc} */
	public void checkRecursions(final CompilationTimeStamp timestamp, final IReferenceChain referenceChain) {
		if (referenceChain.add(this) && patternstring != null) {
			patternstring.checkRecursions(timestamp, referenceChain);
		}
	}

	@Override
	/** {@inheritDoc} */
	protected boolean memberAccept(final ASTVisitor v) {
		if (!super.memberAccept(v)) {
			return false;
		}
		if (patternstring != null && !patternstring.accept(v)) {
			return false;
		}
		return true;
	}

	@Override
	/** {@inheritDoc} */
	public boolean hasSingleExpression() {
		if (lengthRestriction != null || isIfpresent /* TODO:  || get_needs_conversion()*/) {
			return false;
		}

		//TODO maybe can be optimized by analyzing the pattern
		return false;
	}

	@Override
	/** {@inheritDoc} */
	public void generateCodeInit(final JavaGenData aData, final StringBuilder source, final String name) {
		lastTimeBuilt = aData.getBuildTimstamp();

		final StringBuilder preamble = new StringBuilder();
		final String returnValue = patternstring.create_charstring_literals(myScope.getModuleScopeGen(), preamble);

		aData.addBuiltinTypeImport( "TitanCharString" );
		aData.addBuiltinTypeImport( "Base_Template.template_sel" );
		final String escaped = Charstring_Value.get_stringRepr(returnValue);

		source.append(preamble);
		source.append(MessageFormat.format("{0}.operator_assign(new {1}(template_sel.STRING_PATTERN, new TitanCharString({2}), {3}));\n", name, myGovernor.getGenNameTemplate(aData, source), create_charstring_literals(preamble, aData), patternstring.get_nocase()));

		if (lengthRestriction != null) {
			if(getCodeSection() == CodeSectionType.CS_POST_INIT) {
				lengthRestriction.reArrangeInitCode(aData, source, myScope.getModuleScopeGen());
			}
			lengthRestriction.generateCodeInit(aData, source, name);
		}

		if (isIfpresent) {
			source.append(name);
			source.append(".set_ifPresent();\n");
		}
	}

	@Override
	/** {@inheritDoc} */
	public StringBuilder getSingleExpression(final JavaGenData aData, final boolean castIsNeeded) {
		final StringBuilder result = new StringBuilder();

		if (castIsNeeded && (lengthRestriction != null || isIfpresent)) {
			ErrorReporter.INTERNAL_ERROR("FATAL ERROR while processing string pattern template `" + getFullName() + "''");
			return result;
		}

		if (myGovernor == null ) {
			ErrorReporter.INTERNAL_ERROR("FATAL ERROR while processing string pattern template `" + getFullName() + "''");
			return result;
		}

		aData.addBuiltinTypeImport( "TitanCharString" );
		aData.addBuiltinTypeImport( "Base_Template.template_sel" );
		final String escaped = Charstring_Value.get_stringRepr(patternstring.getFullString());
		result.append( MessageFormat.format( "new {0}(template_sel.STRING_PATTERN, new TitanCharString(\"{1}\"), {2})", myGovernor.getGenNameTemplate(aData, result), create_charstring_literals(null, aData), patternstring.get_nocase()));

		//TODO handle cast needed

		return result;
	}
	
	/**
	 * Parse a String to a Reference type.
	 * 
	 * @param refToParse - the reference which will be parsed.
	 * @return the parsed reference
	 */
	public Reference parseRegexp(final String refToParse) {
		TTCN3ReferenceAnalyzer analyzer = new TTCN3ReferenceAnalyzer();
		Reference parsedReference = analyzer.parse((IFile) patternstring.getLocation().getFile(), refToParse, false, patternstring.getLocation().getLine(), patternstring.getLocation().getOffset());
		parsedReference.setCodeSection(getCodeSection());
		parsedReference.setMyScope(getMyScope());
		return parsedReference;
	}

	//semantic check for charstring patterns
	public void checkRef(final Reference reference, final PatternType pstr_type, final Expected_Value_type expected_value, final CompilationTimeStamp timestamp) {
		IValue v = null;
		IValue v_last = null;
		if (reference.getId().getName() == "UNIVERSAL_CHARSTRING") {
			return;
		}
		Assignment ass = reference.getRefdAssignment(timestamp, false);
		if (ass == null) {
			return;
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
		IType ref_type = ass.getType(timestamp).getTypeRefdLast(timestamp).getFieldType(timestamp, reference, 1, expected_value, null, false);
		Type_type tt;
		if (pstr_type == PatternType.UNIVCHARSTRING_PATTERN && ref_type.getTypetype() != Type_type.TYPE_CHARSTRING && ref_type.getTypetype() != Type_type.TYPE_UCHARSTRING) {
			reference.getLocation().reportSemanticError(MessageFormat.format("Type of the referenced {0} {1} should be either 'charstring' or 'universal charstring'",  ass.getAssignmentName(), reference.getDisplayName()));
		} else {
			tt = ref_type.getTypetype();
		}
		IType refcheckertype = null;
		if (ref_type.getTypetype() == Type_type.TYPE_CHARSTRING) {
			refcheckertype = new CharString_Type();
		} else if (ref_type.getTypetype() == Type_type.TYPE_UCHARSTRING) {
			refcheckertype = new UniversalCharstring_Type();
		}
		switch (ass.getAssignmentType()) {
		case A_TYPE:
			break;
		case A_MODULEPAR_TEMPLATE:
		case A_VAR_TEMPLATE:
		case A_PAR_TEMP_IN:
		case A_PAR_TEMP_OUT:
		case A_PAR_TEMP_INOUT:
			// error reporting moved up
			break;
		case A_TEMPLATE:
			ITTCN3Template templ = null;
			templ = ((Def_Template) ass).getTemplate(timestamp);
			refcheckertype.checkThisTemplateRef(timestamp, templ);
			switch (templ.getTemplatetype()) {
			case SPECIFIC_VALUE:
				v_last = templ.getValue();
				break;
				//TODO: template concat in RT2
			case CSTR_PATTERN:
				v_last = this.getPatternstring().get_value();
				break;
			default:
				reference.getLocation().reportSemanticError(MessageFormat.format("Unable to resolve referenced {0} to character string type. {1} template cannot be used.", reference.getDisplayName(), templ.getTemplateTypeName()));
				break;
			}
			break;
		case A_VAR:
		default:
			v = new Referenced_Value(reference);
			v.setMyGovernor(refcheckertype);
			v.setMyScope(reference.getMyScope());
			v.setLocation(reference.getLocation());
			refcheckertype.checkThisValueRef(lastTimeChecked, v);
			v_last = v.getValueRefdLast(timestamp, null);
		}
		v = null;
	}

	public String create_charstring_literals(final StringBuilder preamble, final JavaGenData aData) {
		int parent = 0; 
		StringBuilder s = new StringBuilder();
		//escaped value
		String ttcnPattern = Charstring_Value.get_stringRepr(patternstring.getFullString());
		Matcher m = PATTERN_DYNAMIC_REFERENCE.matcher( ttcnPattern );
		//no reference in the pattern
		if (!m.matches()) {
			s.append("new TitanCharString(\"");
			s.append(ttcnPattern);
			s.append("\")");
			return s.toString();
		}
		while ( m.matches() ) {
			//characters before reference
			if (m.group(1) != null && !m.group(1).isEmpty()) {
				if (m.group(2) != null && !m.group(2).isEmpty()) {
					s.append("new TitanCharString(\"");
					s.append(m.group(1));
					s.append("\").operator_concatenate(");
					parent++;
				} else {
					s.append("new TitanCharString(\"");
					s.append(m.group(1));
					s.append("\")");
				}
			}
			//the reference
			String ref = m.group(2);
			Reference parsedRef = parseRegexp(ref);
			checkRef(parsedRef, PatternType.UNIVCHARSTRING_PATTERN, Expected_Value_type.EXPECTED_DYNAMIC_VALUE, CompilationTimeStamp.getBaseTimestamp());
			ExpressionStruct expr = new ExpressionStruct();
			parsedRef.generateConstRef(aData, expr);
			Value.generateCodeExpressionOptionalFieldReference(aData, expr, parsedRef);
			if (expr.preamble == null || expr.postamble == null) {
				//TODO: check
			}
			boolean castedFromTemplate = false;
			s.append("new TitanUniversalCharString(");
			s.append(expr.expression);
			Assignment refd_last = parsedRef.getRefdAssignment(CompilationTimeStamp.getBaseTimestamp(), false);
			switch (refd_last.getAssignmentType()) {
			case A_TEMPLATE:
			case A_VAR_TEMPLATE:
			case A_MODULEPAR_TEMPLATE:
			case A_PAR_TEMP_IN:
			case A_PAR_TEMP_OUT:
			case A_PAR_TEMP_INOUT:
				castedFromTemplate = true;
				s.append(".castForPatterns()");
				break;
			default:
				break;
			}
			if (refd_last.getType(CompilationTimeStamp.getBaseTimestamp()).getTypetype() == Type_type.TYPE_UCHARSTRING && !castedFromTemplate) {
				s.append(".get_stringRepr_for_pattern()");
			}
			//characters after the reference
			if (m.group(3) != null && !m.group(3).isEmpty()) { 
				s.append(").operator_concatenate(");
				parent++;	
			} else {
				s.append(")");
			}
			ttcnPattern = m.group(3);
			m = PATTERN_DYNAMIC_REFERENCE.matcher( ttcnPattern );
		}
		//remaining characters
		if (ttcnPattern != null && !ttcnPattern.isEmpty()) {
			s.append("new TitanCharString(\"");
			s.append(ttcnPattern);
			s.append("\")");
		}
		while(parent > 0) {
			s.append(")");
			parent--;
		}
		return s.toString();
	}
}
