/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter;

/**
 * Part of the representation of the ASN.1 unrestricted string (CHARACTER STRING) type.
 *
 * @author Kristof Szabados
 */
public class TitanCharacter_String_identification_context__negotiation_template extends Base_Template {
	private TitanInteger_template presentation__context__id; //ASN1_Integer_Type
	private TitanObjectid_template transfer__syntax; //ObjectID_Type
	//originally value_list/list_value
	private List<TitanCharacter_String_identification_context__negotiation_template> list_value;


	/**
	 * Initializes to unbound/uninitialized template.
	 * */
	public TitanCharacter_String_identification_context__negotiation_template() {
		// do nothing
	}

	/**
	 * Initializes to a given template kind.
	 *
	 * @param otherValue
	 *                the template kind to initialize to.
	 * */
	public TitanCharacter_String_identification_context__negotiation_template(final template_sel otherValue ) {
		super( otherValue );
		check_single_selection( otherValue );
	}

	/**
	 * Initializes to a given value.
	 * The template becomes a specific template and the elements of the provided value are copied.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanCharacter_String_identification_context__negotiation_template( final TitanCharacter_String_identification_context__negotiation otherValue ) {
		copyValue(otherValue);
	}

	/**
	 * Initializes to a given template.
	 * The elements of the provided template are copied.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanCharacter_String_identification_context__negotiation_template( final TitanCharacter_String_identification_context__negotiation_template otherValue ) {
		copyTemplate( otherValue );
	}

	/**
	 * Initializes to a given value.
	 * The template becomes a specific template with the provided value.
	 * Causes a dynamic testcase error if the value is neither present nor optional.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanCharacter_String_identification_context__negotiation_template( final Optional<TitanCharacter_String_identification_context__negotiation> otherValue ) {
		switch (otherValue.get_selection()) {
		case OPTIONAL_PRESENT:
			copyValue(otherValue.constGet());
			break;
		case OPTIONAL_OMIT:
			set_selection(template_sel.OMIT_VALUE);
			break;
		default:
			throw new TtcnError("Creating a template of type CHARACTER STRING.identification.context-negotiation from an unbound optional field.");
		}
	}

	@Override
	public TitanCharacter_String_identification_context__negotiation_template assign( final template_sel otherValue ) {
		check_single_selection(otherValue);
		clean_up();
		set_selection(otherValue);
		return this;
	}

	/**
	 * Assigns the other value to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanCharacter_String_identification_context__negotiation_template assign( final TitanCharacter_String_identification_context__negotiation otherValue ) {
		clean_up();
		copyValue(otherValue);
		return this;
	}

	/**
	 * Assigns the other template to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanCharacter_String_identification_context__negotiation_template assign( final TitanCharacter_String_identification_context__negotiation_template otherValue ) {
		if (otherValue != this) {
			clean_up();
			copyTemplate(otherValue);
		}
		return this;
	}

	@Override
	public TitanCharacter_String_identification_context__negotiation_template assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanCharacter_String_identification_context__negotiation) {
			return assign((TitanCharacter_String_identification_context__negotiation) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `TitanCharacter_String_identification_context__negotiation' can not be cast to {1}", otherValue));
	}

	@Override
	public TitanCharacter_String_identification_context__negotiation_template assign(final Base_Template otherValue) {
		if (otherValue instanceof TitanCharacter_String_identification_context__negotiation_template) {
			return assign((TitanCharacter_String_identification_context__negotiation_template) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `TitanCharacter_String_identification_context__negotiation' can not be cast to {1}_template", otherValue));
	}

	/**
	 * Assigns the other value to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanCharacter_String_identification_context__negotiation_template assign( final Optional<TitanCharacter_String_identification_context__negotiation> otherValue ) {
		clean_up();
		switch (otherValue.get_selection()) {
		case OPTIONAL_PRESENT:
			copyValue(otherValue.constGet());
			break;
		case OPTIONAL_OMIT:
			set_selection(template_sel.OMIT_VALUE);
			break;
		default:
			throw new TtcnError("Assignment of an unbound optional field to a template of type CHARACTER STRING.identification.context-negotiation.");
		}
		return this;
	}

	private void copyValue(final TitanCharacter_String_identification_context__negotiation other_value) {
		if (other_value.getpresentation__context__id().is_bound()) {
			getpresentation__context__id().assign(other_value.getpresentation__context__id());
		} else {
			getpresentation__context__id().clean_up();
		}
		if (other_value.gettransfer__syntax().is_bound()) {
			gettransfer__syntax().assign(other_value.gettransfer__syntax());
		} else {
			gettransfer__syntax().clean_up();
		}
		set_selection(template_sel.SPECIFIC_VALUE);
	}

	private void copyTemplate(final TitanCharacter_String_identification_context__negotiation_template other_value) {
		switch (other_value.template_selection) {
		case SPECIFIC_VALUE:
			if (template_sel.UNINITIALIZED_TEMPLATE == other_value.getpresentation__context__id().get_selection()) {
				getpresentation__context__id().clean_up();
			} else {
				getpresentation__context__id().assign(other_value.getpresentation__context__id());
			}
			if (template_sel.UNINITIALIZED_TEMPLATE == other_value.gettransfer__syntax().get_selection()) {
				gettransfer__syntax().clean_up();
			} else {
				gettransfer__syntax().assign(other_value.gettransfer__syntax());
			}
			break;
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			list_value = new ArrayList<TitanCharacter_String_identification_context__negotiation_template>(other_value.list_value.size());
			for(int i = 0; i < other_value.list_value.size(); i++) {
				final TitanCharacter_String_identification_context__negotiation_template temp = new TitanCharacter_String_identification_context__negotiation_template(other_value.list_value.get(i));
				list_value.add(temp);
			}
			break;
		default:
			throw new TtcnError("Copying an uninitialized template of type CHARACTER STRING.identification.context-negotiation.");
		}
		set_selection(other_value);
	}

	@Override
	public void setType(final template_sel template_type, final int list_length) {
		if (template_type != template_sel.VALUE_LIST && template_type != template_sel.COMPLEMENTED_LIST) {
			throw new TtcnError("Setting an invalid list for a template of type CHARACTER STRING.identification.context-negotiation.");
		}
		clean_up();
		set_selection(template_type);
		list_value = new ArrayList<TitanCharacter_String_identification_context__negotiation_template>(list_length);
		for(int i = 0 ; i < list_length; i++) {
			list_value.add(new TitanCharacter_String_identification_context__negotiation_template());
		}
	}


	@Override
	public boolean is_bound() {
		if (template_selection == template_sel.UNINITIALIZED_TEMPLATE && !is_ifPresent) {
			return false;
		}
		if (template_selection != template_sel.SPECIFIC_VALUE) {
			return true;
		}
		if (presentation__context__id.is_bound()) {
			return true;
		}
		if (transfer__syntax.is_bound()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean is_present(final boolean legacy) {
		return isPresent_(legacy);
	}

	private boolean isPresent_(final boolean legacy) {
		if (template_selection==template_sel.UNINITIALIZED_TEMPLATE) {
			return false;
		}
		return !match_omit_(legacy);
	}

	@Override
	public boolean match_omit(final boolean legacy) {
		return match_omit_(legacy);
	}

	private boolean match_omit_(final boolean legacy) {
		if (is_ifPresent) {
			return true;
		}
		switch (template_selection) {
		case OMIT_VALUE:
		case ANY_OR_OMIT:
			return true;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			if (legacy) {
				for (int l_idx=0; l_idx<list_value.size(); l_idx++) {
					if (list_value.get(l_idx).match_omit_(legacy)) {
						return template_selection==template_sel.VALUE_LIST;
					}
				}
				return template_selection==template_sel.COMPLEMENTED_LIST;
			} // else fall through
		default:
			return false;
		}
	}

	@Override
	public boolean is_value() {
		if (template_selection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
			return false;
		}
		if (!presentation__context__id.is_value()) {
			return false;
		}
		if (!transfer__syntax.is_value()) {
			return false;
		}
		return true;
	}
	/**
	 * Gives access to the field presentation-context-id.
	 * Turning the template into a specific value template if needed.
	 *
	 * @return the field presentation-context-id.
	 * */
	public TitanInteger_template getpresentation__context__id() {
		setSpecific();
		return presentation__context__id;
	}

	/**
	 * Gives read-only access to the field presentation-context-id.
	 * Being called on a non specific value template causes dynamic test case error.
	 *
	 * @return the field presentation-context-id.
	 * */
	public TitanInteger_template constGetpresentation__context__id() {
		if (template_selection != template_sel.SPECIFIC_VALUE) {
			throw new TtcnError("Accessing field presentation-context-id of a non-specific template of type CHARACTER STRING.identification.context-negotiation.");
		}
		return presentation__context__id;
	}

	/**
	 * Gives access to the field transfer-syntax.
	 * Turning the template into a specific value template if needed.
	 *
	 * @return the field transfer-syntax.
	 * */
	public TitanObjectid_template gettransfer__syntax() {
		setSpecific();
		return transfer__syntax;
	}

	/**
	 * Gives read-only access to the field transfer-syntax.
	 * Being called on a non specific value template causes dynamic test case error.
	 *
	 * @return the field transfer-syntax.
	 * */
	public TitanObjectid_template constGettransfer__syntax() {
		if (template_selection != template_sel.SPECIFIC_VALUE) {
			throw new TtcnError("Accessing field transfer-syntax of a non-specific template of type CHARACTER STRING.identification.context-negotiation.");
		}
		return transfer__syntax;
	}

	private void setSpecific() {
		if (template_selection != template_sel.SPECIFIC_VALUE) {
			final template_sel old_selection = template_selection;
			clean_up();
			set_selection(template_sel.SPECIFIC_VALUE);
			presentation__context__id = new TitanInteger_template();
			transfer__syntax = new TitanObjectid_template();
			if (old_selection == template_sel.ANY_VALUE || old_selection == template_sel.ANY_OR_OMIT) {
				presentation__context__id.assign(template_sel.ANY_VALUE);
				transfer__syntax.assign(template_sel.ANY_VALUE);
			}
		}
	}
	/**
	 * Matches the provided value against this template.
	 *
	 * @param other_value the value to be matched.
	 * */
	public boolean match(final TitanCharacter_String_identification_context__negotiation other_value) {
		return match(other_value, false);
	}

	/**
	 * Matches the provided value against this template. In legacy mode
	 * omitted value fields are not matched against the template field.
	 *
	 * @param other_value
	 *                the value to be matched.
	 * @param legacy
	 *                use legacy mode.
	 * */
	public boolean match(final TitanCharacter_String_identification_context__negotiation other_value, final boolean legacy) {
		if (!other_value.is_bound()) {
			return false;
		}
		switch (template_selection) {
		case ANY_VALUE:
		case ANY_OR_OMIT:
			return true;
		case OMIT_VALUE:
			return false;
		case SPECIFIC_VALUE:
			if(!other_value.getpresentation__context__id().is_bound()) {
				return false;
			}
			if(!presentation__context__id.match(other_value.getpresentation__context__id(), legacy)) {
				return false;
			}
			if(!other_value.gettransfer__syntax().is_bound()) {
				return false;
			}
			if(!transfer__syntax.match(other_value.gettransfer__syntax(), legacy)) {
				return false;
			}
			return true;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			for (int list_count = 0; list_count < list_value.size(); list_count++) {
				if (list_value.get(list_count).match(other_value, legacy)) {
					return template_selection == template_sel.VALUE_LIST;
				}
			}
			return template_selection == template_sel.COMPLEMENTED_LIST;
		default:
			throw new TtcnError("Matching an uninitialized/unsupported template of type CHARACTER STRING.identification.context-negotiation.");
		}
	}


	@Override
	public boolean match(final Base_Type otherValue, final boolean legacy) {
		if (otherValue instanceof TitanCharacter_String_identification_context__negotiation) {
			return match((TitanCharacter_String_identification_context__negotiation)otherValue, legacy);
		}

		throw new TtcnError("Internal Error: The left operand of assignment is not of type TitanCharacter_String_identification_context__negotiation.");
	}


	@Override
	public TitanCharacter_String_identification_context__negotiation valueOf() {
		if (template_selection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
			throw new TtcnError("Performing a valueof or send operation on a non-specific template of type CHARACTER STRING.identification.context-negotiation.");
		}
		final TitanCharacter_String_identification_context__negotiation ret_val = new TitanCharacter_String_identification_context__negotiation();
		if (presentation__context__id.is_bound()) {
			ret_val.getpresentation__context__id().assign(presentation__context__id.valueOf());
		}
		if (transfer__syntax.is_bound()) {
			ret_val.gettransfer__syntax().assign(transfer__syntax.valueOf());
		}
		return ret_val;
	}
	/**
	 * Returns the size (number of fields).
	 *
	 * size_of in the core
	 *
	 * @return the size of the structure.
	 * */
	public TitanInteger sizeOf() {
		if (is_ifPresent) {
			throw new TtcnError("Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation which has an ifpresent attribute.");
		}
		switch (template_selection) {
		case SPECIFIC_VALUE:
			return new TitanInteger(2);
		case VALUE_LIST:
			if (list_value.isEmpty()) {
				throw new TtcnError("Internal error: Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation containing an empty list.");
			}
			final int item_size = list_value.get(0).sizeOf().getInt();
			for (int l_idx = 1; l_idx < list_value.size(); l_idx++) {
				if (list_value.get(l_idx).sizeOf().getInt() != item_size) {
					throw new TtcnError("Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation containing a value list with different sizes.");
				}
			}
			return new TitanInteger(item_size);
		case OMIT_VALUE:
			throw new TtcnError("Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation containing omit value.");
		case ANY_VALUE:
		case ANY_OR_OMIT:
			throw new TtcnError("Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation containing */? value.");
		case COMPLEMENTED_LIST:
			throw new TtcnError("Performing sizeof() operation on a template of type CHARACTER STRING.identification.context-negotiation containing complemented list.");
		default:
			throw new TtcnError("Performing sizeof() operation on an uninitialized/unsupported template of type CHARACTER STRING.identification.context-negotiation.");
		}
	}

	@Override
	public TitanCharacter_String_identification_context__negotiation_template listItem(final int list_index) {
		if (template_selection != template_sel.VALUE_LIST && template_selection != template_sel.COMPLEMENTED_LIST) {
			throw new TtcnError("Accessing a list element of a non-list template of type CHARACTER STRING.identification.context-negotiation.");
		}
		if (list_index >= list_value.size()) {
			throw new TtcnError("Index overflow in a value list template of type CHARACTER STRING.identification.context-negotiation.");
		}
		return list_value.get(list_index);
	}

	@Override
	public void log() {
		switch (template_selection) {
		case SPECIFIC_VALUE:
			TTCN_Logger.log_char('{');
			TTCN_Logger.log_event_str(" presentation-context-id := ");
			presentation__context__id.log();
			TTCN_Logger.log_char(',');
			TTCN_Logger.log_event_str(" transfer-syntax := ");
			transfer__syntax.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case COMPLEMENTED_LIST:
			TTCN_Logger.log_event_str("complement");
		case VALUE_LIST:
			TTCN_Logger.log_char('(');
			for (int list_count = 0; list_count < list_value.size(); list_count++) {
				if (list_count > 0) {
					TTCN_Logger.log_event_str(", ");
				}
				list_value.get(list_count).log();
			}
			TTCN_Logger.log_char(')');
			break;
		default:
			log_generic();
			break;
		}
		log_ifpresent();
	}

	/**
	 * Logs the matching of the provided value to this template, to help
	 * identify the reason for mismatch.
	 *
	 * @param match_value
	 *                the value to be matched.
	 * */
	public void log_match(final TitanCharacter_String_identification_context__negotiation match_value) {
		log_match(match_value, false);
	}

	@Override
	public void log_match(final Base_Type match_value, final boolean legacy) {
		if (match_value instanceof TitanCharacter_String_identification_context__negotiation) {
			log_match((TitanCharacter_String_identification_context__negotiation)match_value, legacy);
			return;
		}

		throw new TtcnError("Internal Error: value can not be cast to CHARACTER STRING.identification.context-negotiation.");
	}

	/**
	 * Logs the matching of the provided value to this template, to help
	 * identify the reason for mismatch. In legacy mode omitted value fields
	 * are not matched against the template field.
	 *
	 * @param match_value
	 *                the value to be matched.
	 * @param legacy
	 *                use legacy mode.
	 * */
	public void log_match(final TitanCharacter_String_identification_context__negotiation match_value, final boolean legacy) {
		if ( TTCN_Logger.matching_verbosity_t.VERBOSITY_COMPACT == TTCN_Logger.get_matching_verbosity() ) {
			if(match(match_value, legacy)) {
				TTCN_Logger.print_logmatch_buffer();
				TTCN_Logger.log_event_str(" matched");
			} else {
				if (template_selection == template_sel.SPECIFIC_VALUE) {
					final int previous_size = TTCN_Logger.get_logmatch_buffer_len();
					if( !presentation__context__id.match(match_value.constGetpresentation__context__id(), legacy) ) {
						TTCN_Logger.log_logmatch_info(".presentation-context-id");
						presentation__context__id.log_match(match_value.constGetpresentation__context__id(), legacy);
						TTCN_Logger.set_logmatch_buffer_len(previous_size);
					}
					if( !transfer__syntax.match(match_value.constGettransfer__syntax(), legacy) ) {
						TTCN_Logger.log_logmatch_info(".transfer-syntax");
						transfer__syntax.log_match(match_value.constGettransfer__syntax(), legacy);
						TTCN_Logger.set_logmatch_buffer_len(previous_size);
					}
				} else {
					TTCN_Logger.print_logmatch_buffer();
					match_value.log();
					TTCN_Logger.log_event_str(" with ");
					log();
					TTCN_Logger.log_event_str(" unmatched");
				}
			}
			return;
		}
		if (template_selection == template_sel.SPECIFIC_VALUE) {
			TTCN_Logger.log_event_str("{ presentation-context-id := ");
			presentation__context__id.log_match(match_value.constGetpresentation__context__id(), legacy);
			TTCN_Logger.log_event_str("{ transfer-syntax := ");
			transfer__syntax.log_match(match_value.constGettransfer__syntax(), legacy);
			TTCN_Logger.log_event_str(" }");
		} else {
			match_value.log();
			TTCN_Logger.log_event_str(" with ");
			log();
			if ( match(match_value, legacy) ) {
				TTCN_Logger.log_event_str(" matched");
			} else {
				TTCN_Logger.log_event_str(" unmatched");
			}
		}
	}

	@Override
	public void encode_text(final Text_Buf text_buf) {
		encode_text_base(text_buf);
		switch (template_selection) {
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case SPECIFIC_VALUE:
			presentation__context__id.encode_text(text_buf);
			transfer__syntax.encode_text(text_buf);
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			text_buf.push_int(list_value.size());
			for (int i = 0; i < list_value.size(); i++) {
				list_value.get(i).encode_text(text_buf);
			}
			break;
		default:
			throw new TtcnError("Text encoder: Encoding an uninitialized/unsupported template of type CHARACTER STRING.identification.context-negotiation.");
		}
	}

	@Override
	public void decode_text(final Text_Buf text_buf) {
		clean_up();
		decode_text_base(text_buf);
		switch (template_selection) {
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case SPECIFIC_VALUE:
			presentation__context__id = new TitanInteger_template();
			presentation__context__id.decode_text(text_buf);
			transfer__syntax = new TitanObjectid_template();
			transfer__syntax.decode_text(text_buf);
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST: {
			final int size = text_buf.pull_int().getInt();
			list_value = new ArrayList<TitanCharacter_String_identification_context__negotiation_template>(size);
			for (int i = 0; i < size; i++) {
				final TitanCharacter_String_identification_context__negotiation_template temp = new TitanCharacter_String_identification_context__negotiation_template();
				temp.decode_text(text_buf);
				list_value.add(temp);
			}
			break;
		}
		default:
			throw new TtcnError("Text decoder: An unknown/unsupported selection was received in a template of type CHARACTER STRING.identification.context-negotiation.");
		}
	}

	@Override
	public void set_param(final Module_Parameter param) {
		param.basic_check(Module_Parameter.basic_check_bits_t.BC_TEMPLATE.getValue(), "record template");
		switch (param.get_type()) {
		case MP_Omit:
			assign(template_sel.OMIT_VALUE);
			break;
		case MP_Any:
			assign(template_sel.ANY_VALUE);
			break;
		case MP_AnyOrNone:
			assign(template_sel.ANY_OR_OMIT);
			break;
		case MP_List_Template:
		case MP_ComplementList_Template: {
			final int size = param.get_size();
			setType(param.get_type() == Module_Parameter.type_t.MP_List_Template ? template_sel.VALUE_LIST : template_sel.COMPLEMENTED_LIST, size);
			for (int i = 0; i < size; i++) {
				listItem(i).set_param(param.get_elem(i));
			}
			break;
		}
		case MP_Value_List:
			if (param.get_size() > 2) {
				param.error(MessageFormat.format("record template of type CHARACTER STRING.identification.context-negotiation has 2 fields but list value has {0} fields.", param.get_size()));
			}
			if (param.get_size() > 0 && param.get_elem(0).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				getpresentation__context__id().set_param(param.get_elem(0));
			}
			if (param.get_size() > 1 && param.get_elem(1).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				gettransfer__syntax().set_param(param.get_elem(1));
			}
			break;
		case MP_Assignment_List: {
			final boolean value_used[] = new boolean[param.get_size()];
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("presentation-context-id".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						getpresentation__context__id().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("transfer-syntax".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						gettransfer__syntax().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				if (!value_used[val_idx]) {
					final Module_Parameter curr_param = param.get_elem(val_idx);
					curr_param.error(MessageFormat.format("Non existent field name in type CHARACTER STRING.identification.context-negotiation: {0}", curr_param.get_id().get_name()));
					break;
				}
			}
			break;
		}
		default:
			param.type_error("record template", "CHARACTER STRING.identification.context-negotiation");
		}
		is_ifPresent = param.get_ifpresent();
	}

	@Override
	public void check_restriction(final template_res restriction, final String name, final boolean legacy) {
		if (template_selection == template_sel.UNINITIALIZED_TEMPLATE) {
			return;
		}
		switch ((name != null && restriction == template_res.TR_VALUE) ? template_res.TR_OMIT : restriction) {
		case TR_OMIT:
			if (template_selection == template_sel.OMIT_VALUE) {
				return;
			}
		case TR_VALUE:
			if (template_selection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
				break;
			}
			this.presentation__context__id.check_restriction(restriction, name == null ? "CHARACTER STRING.identification.context-negotiation" : name, legacy);
			this.transfer__syntax.check_restriction(restriction, name == null ? "CHARACTER STRING.identification.context-negotiation" : name, legacy);
			return;
		case TR_PRESENT:
			if (!match_omit(legacy)) {
				return;
			}
			break;
		default:
			return;
		}
		throw new TtcnError(MessageFormat.format("Restriction `{0}'' on template of type {1} violated.", get_res_name(restriction), name == null ? "CHARACTER STRING.identification.context-negotiation" : name));
	}
}