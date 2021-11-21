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
    SimpleList,
    ArrayInput,
    SimpleFormIterator,
    DateInput,
    DateTimeInput,
    DateField,
    TopToolbar,
    CreateButton,
    ExportButton,
    useTranslate,
    BooleanInput,
    NumberInput
} from 'react-admin';
import { useMediaQuery } from '@material-ui/core';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import Checkbox from '@material-ui/core/Checkbox';
import Button from '@material-ui/core/Button';
import Emitter from "../../emitter";
import { FormControlLabel } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import VisibilityIcon from '@material-ui/icons/Visibility';
import { useSelector, useDispatch } from 'react-redux'
import { show } from "./slicer/entityShowColumnSlicer";
import AxiosInstance from "../../AxiosInstance";
import {entityExt, EntityListCustomButtons} from "./ext/entityExt";
import { useFormState, useForm } from 'react-final-form';

import Title from "./edit/fields/Title";
import ClassName from "./edit/fields/ClassName";
import Description from "./edit/fields/Description";
import Name from "./edit/fields/Name";
import PackageName from "./edit/fields/PackageName";
import Tenant from "./edit/fields/Tenant";

const columnStates = {
		title: {list: true},
		className: {list: true},
		description: {list: true},
		name: {list: true},
		packageName: {list: true},
		tenant: {list: true},
    }
    
export const EntityList = (props) => {
    const isSmall = useMediaQuery(theme => theme.breakpoints.down('sm'));
    
    const [columns, setColumns]=React.useState({})
    
    const entityShowColumnSlicer = useSelector(
        (state) => state.entityShowColumnSlicer
    )
    
    React.useEffect( () => {
		//const sessionColumns = sessionStorage.getItem("columns")
        //if(sessionColumns) {
        //    console.log("sessionColumns: " + sessionColumns)
        //    const parsedColumns = JSON.parse(sessionColumns)
        //    setColumns(parsedColumns)
        //}
        //else {
        //    setColumns(columnStates)
        //}
        
        //Emitter.on("show_column", ({columnId, show}) => {
         //   console.log("column is showing: " + columnId + ", show is: " + show)
         //   columns[columnId].list=show
         //   setColumns({...columns})
         //   sessionStorage.setItem("columns", JSON.stringify(columns))

        //})
    }, [])
    
     React.useEffect( () => {
		console.log(entityShowColumnSlicer)
        setColumns({...entityShowColumnSlicer})
    }, [entityShowColumnSlicer])
    
    return (
        <List {...props} filters={<EntityFilter />} actions={<ListActions />}>
            {isSmall ? (
                <SimpleList
                    primaryText={record => record.firstname}
                    secondaryText={record => `${record.views} views`}
                    tertiaryText={record => new Date(record.published_at).toLocaleDateString()}
                />
            ) : (
                <Datagrid>
                    
                    {columns.title?.list &&
                    <TextField source="title" />
                    }
                    
                    {columns.className?.list &&
                    <TextField source="className" />
                    }
                    
                    {columns.description?.list &&
                    <TextField source="description" />
                    }
                    
                    {columns.name?.list &&
                    <TextField source="name" />
                    }
                    
                    {columns.packageName?.list &&
                    <TextField source="packageName" />
                    }
                    
                    
                    {columns.tenant?.list &&
                    <ReferenceField label="Tenant" source="tenant" reference="tenant">
                    	<div>
                        <TextField source="title" /> &nbsp; 
                        </div>
                    </ReferenceField>
                    }
                    <EditButton />
                    <EntityListCustomButtons />
                </Datagrid>
            )}
        </List>
    );
}

export const EntityEdit = props => (
    <Edit {...props} mutationMode='optimistic'>
        <SimpleForm>
            <Title {...props} formAction="edit" />
            <ClassName {...props} formAction="edit" />
            <Description {...props} formAction="edit" />
            <Name {...props} formAction="edit" />
            <PackageName {...props} formAction="edit" />
            <Tenant {...props} formAction="edit" />
        </SimpleForm>
    </Edit>
);

export const EntityCreate = props => (
    <Create {...props} mutationMode='optimistic'>
        <SimpleForm>
	        <Title {...props} formAction="create" />
	        <ClassName {...props} formAction="create" />
	        <Description {...props} formAction="create" />
	        <Name {...props} formAction="create" />
	        <PackageName {...props} formAction="create" />
	        <Tenant {...props} formAction="create" />
            <EntityCreateForm {...props}/>
        </SimpleForm>
    </Create>
);

const EntityCreateForm = props => {
    const { values } = useFormState()
    const form = useForm()


    React.useEffect(() => {
        console.log(values)
        entityExt.edited(values, form)
    }, [values])

    return (<div></div>)
}

const EntityFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Title" source="title" allowEmpty />
        <TextInput label="ClassName" source="className" allowEmpty />
        <TextInput label="Description" source="description" allowEmpty />
        <TextInput label="name" source="name" allowEmpty />
        <TextInput label="packageName" source="packageName" allowEmpty />
        <ReferenceInput label="Tenant" source="tenant" reference="tenant" 
        	perPage={100} 
            onChange={(e) => {
            	e.preventDefault()
                return true
            }}>
            <SelectInput  {...props}
             	optionText={record => <TenantOptionsRenderer record={record} /> }
            />
        </ReferenceInput>
    </Filter>
);
const TenantOptionsRenderer = props => {
    const [title, setTitle] = React.useState("")

    React.useEffect(async () => {
        const record = props.record
        
        const t = record.title
        setTitle(t)
    }, [])

    return title
    
}


const ListActions = (props) => (
    <TopToolbar>
        {<ColumnsMenu />}
        {React.cloneElement(props.filters, { context: 'button' })}
        <CreateButton/>
        <ExportButton/>
        {/* Add your custom actions */}
        
    </TopToolbar>
);

const ColumnsMenu = props => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const classes = useStyles(props);
    const [columns, setColumns]=React.useState({})
    const dispatch = useDispatch()
    const t = useTranslate()

    React.useEffect(() => {
        const sessionColumns = sessionStorage.getItem("god_entity_columns")

        if(sessionColumns != null) {
            console.log("sessionColumns: " + sessionColumns)
            const parsedColumns = JSON.parse(sessionColumns)
            setColumns(parsedColumns)
            Object.keys(parsedColumns).forEach(c => {
                dispatch(show({[c]: parsedColumns[c]}))
            })
        }
        else {
            setColumns(columnStates)
            Object.keys(columnStates).forEach(c => {
                dispatch(show({[c]: columnStates[c]}))
            })
        }
    }, [])


    const handleClick = (event) => {
        setAnchorEl(event.currentTarget)
    }
    
    const handleClose = () => {
        setAnchorEl(null)
    }

    const showColumn = (e, columnId) => {
        const showed = e.target.checked
        const v = {list: showed}
        setColumns({...columns, [columnId]: v})
		sessionStorage.setItem("god_entity_columns", JSON.stringify({...columns, [columnId]: v}))
		const showData={}
        showData[columnId] = {list: showed}
        dispatch(show(showData))
        //Emitter.emit("show_column", {columnId: columnId, show: e.target.checked})
    }

    return (
        <div>
        <Button size="small" color="primary" onClick={handleClick} startIcon={<VisibilityIcon />}
        className={classes.sizeSmall, classes.button, classes.RaButton, classes.root, classes.text}>
            {t('Show Columns')}
        </Button>
            <Menu 
            anchorEl={anchorEl}
            keepMounted
            open={Boolean(anchorEl)}
            onClose={handleClose}>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'title')} checked={columns.title?.list}  />}
                	label={t('resources.entity.fields.title')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'className')} checked={columns.className?.list}  />}
                	label={t('resources.entity.fields.className')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'description')} checked={columns.description?.list}  />}
                	label={t('resources.entity.fields.description')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'name')} checked={columns.name?.list}  />}
                	label={t('resources.entity.fields.name')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'packageName')} checked={columns.packageName?.list}  />}
                	label={t('resources.entity.fields.packageName')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'tenant')} checked={columns.tenant?.list}  />}
                	label={t('resources.entity.fields.tenant')}
                />
                </MenuItem>
            </Menu>
        </div>
    );
}


const useStyles = makeStyles(
    {
       
    }
);