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
    AutocompleteInput,
    ArrayInput,
    SimpleFormIterator
} from 'react-admin';

import { useForm, useFormState } from 'react-final-form'

import Emitter from "../../../../emitter";

const ReferenceTitles = props => {
    const form = useForm();
    const dataProvider = useDataProvider();
    const { values } = useFormState()

    return (
		<ArrayInput {...props} source="referenceTitles" disabled={values.disableReferenceTitles}>
            <SimpleFormIterator>
                <TextInput  />
            </SimpleFormIterator>
        </ArrayInput>
    )
}

export default ReferenceTitles