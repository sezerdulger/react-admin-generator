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

const PlayableVideo = props => {
    const form = useForm();
    const dataProvider = useDataProvider();
    const { values } = useFormState()

    return (
        <BooleanInput {...props} label="Is playable video?" source="playableVideo" disabled={values.disablePlayableVideo} />
    )
}

export default PlayableVideo