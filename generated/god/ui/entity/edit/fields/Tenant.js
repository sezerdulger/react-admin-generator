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
import Emitter from "../../../../emitter";
import { useForm, useFormState } from 'react-final-form'
import AxiosInstance from "../../../../AxiosInstance";

const Tenant = props => {
    const form = useForm();
    const dataProvider = useDataProvider();
    const { values } = useFormState()

    return (
        <ReferenceInput {...props} label="Tenant" source="tenant" reference="tenant" 
        	perPage={100} 
            onChange={(e) => {
            	e.preventDefault()
                 
                Emitter.emit("entity_tenant_changed", {event: e, form: form, dataProvider: dataProvider, formAction: props.formAction })

                return true
            }}
            disabled={values.disableTenant}>
            <SelectInput  {...props}
             	optionText={record => <TenantOptionsRenderer record={record} /> }
            />
        </ReferenceInput>
    )
}

const TenantOptionsRenderer = props => {
    const [title, setTitle] = React.useState("")

    React.useEffect(async () => {
        const record = props.record
        
        const t = record.title
        setTitle(t)
    }, [])

    return title
    
}

export default Tenant