// in src/users.js
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
    SimpleList
} from 'react-admin';
import { useMediaQuery } from '@material-ui/core';

export const UserList = (props) => {
    const isSmall = useMediaQuery(theme => theme.breakpoints.down('sm'));
    return (
        <List {...props} filters={<UserFilter />}>
            {isSmall ? (
                <SimpleList
                    primaryText={record => record.firstname}
                    secondaryText={record => `${record.views} views`}
                    tertiaryText={record => new Date(record.published_at).toLocaleDateString()}
                />
            ) : (
                <Datagrid>
                    <TextField source="firstname" />
                    <TextField source="lastname" />
                    <EditButton />
                </Datagrid>
            )}
        </List>
    );
}

export const UserEdit = props => (
    <Edit {...props}>
        <SimpleForm>
            <TextInput  source="firstname" />
            <TextInput  source="lastname" />
        </SimpleForm>
    </Edit>
);

export const UserCreate = props => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="firstname" />
            <TextInput source="lastname" />
        </SimpleForm>
    </Create>
);

const UserFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Firstname" source="firstname" allowEmpty />
        <TextInput label="Lastname" source="lastname" allowEmpty />
    </Filter>
);