import * as React from "react";
import {
    List,
    Datagrid,
    TextField,
    ReferenceField,
    EditButton,
    Edit,
    SimpleForm,
    ReferenceInput,
    SelectInput,
    BooleanInput,
    Create,
    Filter,
    SimpleList,
    useRecordContext,
    useCreateController,
    FormDataConsumer,
    useGetOne,
    useDataProvider,
    AutocompleteInput
} from 'react-admin';

import { useForm, useFormState } from 'react-final-form'

import Emitter from "../../../../emitter";

const LongText = props => {
    const form = useForm();
    const dataProvider = useDataProvider();
    const { values } = useFormState()

    return (
        <BooleanInput {...props} label="Is text Long?" source="longText" disabled={values.disableLongText} />
    )
}

export default LongText