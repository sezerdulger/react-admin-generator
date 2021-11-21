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
import { show } from "./slicer/fieldShowColumnSlicer";
import AxiosInstance from "../../AxiosInstance";
import {fieldExt, FieldListCustomButtons} from "./ext/fieldExt";
import { useFormState, useForm } from 'react-final-form';

import Name from "./edit/fields/Name";
import Type from "./edit/fields/Type";
import SupType from "./edit/fields/SupType";
import Title from "./edit/fields/Title";
import Description from "./edit/fields/Description";
import LongText from "./edit/fields/LongText";
import File from "./edit/fields/File";
import PlayableVideo from "./edit/fields/PlayableVideo";
import Reference from "./edit/fields/Reference";
import ReferenceMultiple from "./edit/fields/ReferenceMultiple";
import ReferenceTabbed from "./edit/fields/ReferenceTabbed";
import ReferenceAsForm from "./edit/fields/ReferenceAsForm";
import ReferenceEntityName from "./edit/fields/ReferenceEntityName";
import ReferenceTitleFromRecord from "./edit/fields/ReferenceTitleFromRecord";
import ReferenceTitles from "./edit/fields/ReferenceTitles";
import Entity from "./edit/fields/Entity";

const columnStates = {
		name: {list: true},
		type: {list: true},
		supType: {list: true},
		title: {list: true},
		description: {list: true},
		longText: {list: true},
		file: {list: true},
		playableVideo: {list: true},
		reference: {list: true},
		referenceMultiple: {list: true},
		referenceTabbed: {list: true},
		referenceAsForm: {list: true},
		referenceEntityName: {list: true},
		referenceTitleFromRecord: {list: true},
		referenceTitles: {list: true},
		entity: {list: true},
    }
    
export const FieldList = (props) => {
    const isSmall = useMediaQuery(theme => theme.breakpoints.down('sm'));
    
    const [columns, setColumns]=React.useState({})
    
    const fieldShowColumnSlicer = useSelector(
        (state) => state.fieldShowColumnSlicer
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
		console.log(fieldShowColumnSlicer)
        setColumns({...fieldShowColumnSlicer})
    }, [fieldShowColumnSlicer])
    
    return (
        <List {...props} filters={<FieldFilter />} actions={<ListActions />}>
            {isSmall ? (
                <SimpleList
                    primaryText={record => record.firstname}
                    secondaryText={record => `${record.views} views`}
                    tertiaryText={record => new Date(record.published_at).toLocaleDateString()}
                />
            ) : (
                <Datagrid>
                    
                    {columns.name?.list &&
                    <TextField source="name" />
                    }
                    
                    {columns.type?.list &&
                    <TextField source="type" />
                    }
                    
                    {columns.supType?.list &&
                    <TextField source="supType" />
                    }
                    
                    {columns.title?.list &&
                    <TextField source="title" />
                    }
                    
                    {columns.description?.list &&
                    <TextField source="description" />
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    {columns.referenceEntityName?.list &&
                    <TextField source="referenceEntityName" />
                    }
                    
                    {columns.referenceTitleFromRecord?.list &&
                    <TextField source="referenceTitleFromRecord" />
                    }
                    
                    {columns.referenceTitles?.list &&
                    <TextField source="referenceTitles" />
                    }
                    
                    
                    {columns.entity?.list &&
                    <ReferenceField label="Entity" source="entity" reference="entity">
                    	<div>
                        <TextField source="title" /> &nbsp; 
                        </div>
                    </ReferenceField>
                    }
                    <EditButton />
                    <FieldListCustomButtons />
                </Datagrid>
            )}
        </List>
    );
}

export const FieldEdit = props => (
    <Edit {...props} mutationMode='optimistic'>
        <SimpleForm>
            <Name {...props} formAction="edit" />
            <Type {...props} formAction="edit" />
            <SupType {...props} formAction="edit" />
            <Title {...props} formAction="edit" />
            <Description {...props} formAction="edit" />
            <LongText {...props} formAction="edit" />
            <File {...props} formAction="edit" />
            <PlayableVideo {...props} formAction="edit" />
            <Reference {...props} formAction="edit" />
            <ReferenceMultiple {...props} formAction="edit" />
            <ReferenceTabbed {...props} formAction="edit" />
            <ReferenceAsForm {...props} formAction="edit" />
            <ReferenceEntityName {...props} formAction="edit" />
            <ReferenceTitleFromRecord {...props} formAction="edit" />
            <ReferenceTitles {...props} formAction="edit" />
            <Entity {...props} formAction="edit" />
        </SimpleForm>
    </Edit>
);

export const FieldCreate = props => (
    <Create {...props} mutationMode='optimistic'>
        <SimpleForm>
	        <Name {...props} formAction="create" />
	        <Type {...props} formAction="create" />
	        <SupType {...props} formAction="create" />
	        <Title {...props} formAction="create" />
	        <Description {...props} formAction="create" />
	        <LongText {...props} formAction="create" />
	        <File {...props} formAction="create" />
	        <PlayableVideo {...props} formAction="create" />
	        <Reference {...props} formAction="create" />
	        <ReferenceMultiple {...props} formAction="create" />
	        <ReferenceTabbed {...props} formAction="create" />
	        <ReferenceAsForm {...props} formAction="create" />
	        <ReferenceEntityName {...props} formAction="create" />
	        <ReferenceTitleFromRecord {...props} formAction="create" />
	        <ReferenceTitles {...props} formAction="create" />
	        <Entity {...props} formAction="create" />
            <FieldCreateForm {...props}/>
        </SimpleForm>
    </Create>
);

const FieldCreateForm = props => {
    const { values } = useFormState()
    const form = useForm()


    React.useEffect(() => {
        console.log(values)
        fieldExt.edited(values, form)
    }, [values])

    return (<div></div>)
}

const FieldFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Name" source="name" allowEmpty />
        <TextInput label="Type" source="type" allowEmpty />
        <TextInput label="SuperType" source="supType" allowEmpty />
        <TextInput label="Title" source="title" allowEmpty />
        <TextInput label="Description" source="description" allowEmpty />
        <BooleanInput label="Is text Long?" source="longText" allowEmpty />
        <BooleanInput label="Is File?" source="file" allowEmpty />
        <BooleanInput label="Is playable video?" source="playableVideo" allowEmpty />
        <BooleanInput label="Is reference?" source="reference" allowEmpty />
        <BooleanInput label="Is reference multiple?" source="referenceMultiple" allowEmpty />
        <BooleanInput label="Is reference tabbed?" source="referenceTabbed" allowEmpty />
        <BooleanInput label="Is reference as form?" source="referenceAsForm" allowEmpty />
        <TextInput label="Reference Entity Name" source="referenceEntityName" allowEmpty />
        <TextInput label="Reference Title From Record record =>" source="referenceTitleFromRecord" allowEmpty />
    	<ArrayInput source="referenceTitles" label="Reference Titles for List">
            <SimpleFormIterator>
                <TextInput  />
            </SimpleFormIterator>
        </ArrayInput>
        <ReferenceInput label="Entity" source="entity" reference="entity" 
        	perPage={100} 
            onChange={(e) => {
            	e.preventDefault()
                return true
            }}>
            <SelectInput  {...props}
             	optionText={record => <EntityOptionsRenderer record={record} /> }
            />
        </ReferenceInput>
    </Filter>
);
const EntityOptionsRenderer = props => {
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
        const sessionColumns = sessionStorage.getItem("god_field_columns")

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
		sessionStorage.setItem("god_field_columns", JSON.stringify({...columns, [columnId]: v}))
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
                    <Checkbox onChange={e => showColumn(e, 'name')} checked={columns.name?.list}  />}
                	label={t('resources.field.fields.name')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'type')} checked={columns.type?.list}  />}
                	label={t('resources.field.fields.type')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'supType')} checked={columns.supType?.list}  />}
                	label={t('resources.field.fields.supType')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'title')} checked={columns.title?.list}  />}
                	label={t('resources.field.fields.title')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'description')} checked={columns.description?.list}  />}
                	label={t('resources.field.fields.description')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'longText')} checked={columns.longText?.list}  />}
                	label={t('resources.field.fields.longText')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'file')} checked={columns.file?.list}  />}
                	label={t('resources.field.fields.file')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'playableVideo')} checked={columns.playableVideo?.list}  />}
                	label={t('resources.field.fields.playableVideo')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'reference')} checked={columns.reference?.list}  />}
                	label={t('resources.field.fields.reference')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceMultiple')} checked={columns.referenceMultiple?.list}  />}
                	label={t('resources.field.fields.referenceMultiple')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceTabbed')} checked={columns.referenceTabbed?.list}  />}
                	label={t('resources.field.fields.referenceTabbed')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceAsForm')} checked={columns.referenceAsForm?.list}  />}
                	label={t('resources.field.fields.referenceAsForm')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceEntityName')} checked={columns.referenceEntityName?.list}  />}
                	label={t('resources.field.fields.referenceEntityName')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceTitleFromRecord')} checked={columns.referenceTitleFromRecord?.list}  />}
                	label={t('resources.field.fields.referenceTitleFromRecord')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'referenceTitles')} checked={columns.referenceTitles?.list}  />}
                	label={t('resources.field.fields.referenceTitles')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'entity')} checked={columns.entity?.list}  />}
                	label={t('resources.field.fields.entity')}
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