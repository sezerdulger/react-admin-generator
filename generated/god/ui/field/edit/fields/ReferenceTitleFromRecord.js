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
    TextInput,
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

const ReferenceTitleFromRecord = props => {
    const form = useForm();
    const dataProvider = useDataProvider();
    const { values } = useFormState()

    return (
        <TextInput {...props}  source="referenceTitleFromRecord" disabled={values.disableReferenceTitleFromRecord} />
    )
}

export default ReferenceTitleFromRecord