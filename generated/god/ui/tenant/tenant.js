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
import { show } from "./slicer/tenantShowColumnSlicer";
import AxiosInstance from "../../AxiosInstance";
import {tenantExt, TenantListCustomButtons} from "./ext/tenantExt";
import { useFormState, useForm } from 'react-final-form';

import Title from "./edit/fields/Title";
import ClassName from "./edit/fields/ClassName";
import Description from "./edit/fields/Description";
import Name from "./edit/fields/Name";
import PackageName from "./edit/fields/PackageName";
import UiSrcPath from "./edit/fields/UiSrcPath";
import JavaSrcPath from "./edit/fields/JavaSrcPath";

const columnStates = {
		title: {list: true},
		className: {list: true},
		description: {list: true},
		name: {list: true},
		packageName: {list: true},
		uiSrcPath: {list: true},
		javaSrcPath: {list: true},
    }
    
export const TenantList = (props) => {
    const isSmall = useMediaQuery(theme => theme.breakpoints.down('sm'));
    
    const [columns, setColumns]=React.useState({})
    
    const tenantShowColumnSlicer = useSelector(
        (state) => state.tenantShowColumnSlicer
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
		console.log(tenantShowColumnSlicer)
        setColumns({...tenantShowColumnSlicer})
    }, [tenantShowColumnSlicer])
    
    return (
        <List {...props} filters={<TenantFilter />} actions={<ListActions />}>
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
                    
                    {columns.uiSrcPath?.list &&
                    <TextField source="uiSrcPath" />
                    }
                    
                    {columns.javaSrcPath?.list &&
                    <TextField source="javaSrcPath" />
                    }
                    <EditButton />
                    <TenantListCustomButtons />
                </Datagrid>
            )}
        </List>
    );
}

export const TenantEdit = props => (
    <Edit {...props} mutationMode='optimistic'>
        <SimpleForm>
            <Title {...props} formAction="edit" />
            <ClassName {...props} formAction="edit" />
            <Description {...props} formAction="edit" />
            <Name {...props} formAction="edit" />
            <PackageName {...props} formAction="edit" />
            <UiSrcPath {...props} formAction="edit" />
            <JavaSrcPath {...props} formAction="edit" />
        </SimpleForm>
    </Edit>
);

export const TenantCreate = props => (
    <Create {...props} mutationMode='optimistic'>
        <SimpleForm>
	        <Title {...props} formAction="create" />
	        <ClassName {...props} formAction="create" />
	        <Description {...props} formAction="create" />
	        <Name {...props} formAction="create" />
	        <PackageName {...props} formAction="create" />
	        <UiSrcPath {...props} formAction="create" />
	        <JavaSrcPath {...props} formAction="create" />
            <TenantCreateForm {...props}/>
        </SimpleForm>
    </Create>
);

const TenantCreateForm = props => {
    const { values } = useFormState()
    const form = useForm()


    React.useEffect(() => {
        console.log(values)
        tenantExt.edited(values, form)
    }, [values])

    return (<div></div>)
}

const TenantFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Title" source="title" allowEmpty />
        <TextInput label="ClassName" source="className" allowEmpty />
        <TextInput label="Description" source="description" allowEmpty />
        <TextInput label="name" source="name" allowEmpty />
        <TextInput label="packageName" source="packageName" allowEmpty />
        <TextInput label="uiSrcPath" source="uiSrcPath" allowEmpty />
        <TextInput label="javaSrcPath" source="javaSrcPath" allowEmpty />
    </Filter>
);


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
        const sessionColumns = sessionStorage.getItem("god_tenant_columns")

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
		sessionStorage.setItem("god_tenant_columns", JSON.stringify({...columns, [columnId]: v}))
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
                	label={t('resources.tenant.fields.title')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'className')} checked={columns.className?.list}  />}
                	label={t('resources.tenant.fields.className')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'description')} checked={columns.description?.list}  />}
                	label={t('resources.tenant.fields.description')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'name')} checked={columns.name?.list}  />}
                	label={t('resources.tenant.fields.name')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'packageName')} checked={columns.packageName?.list}  />}
                	label={t('resources.tenant.fields.packageName')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'uiSrcPath')} checked={columns.uiSrcPath?.list}  />}
                	label={t('resources.tenant.fields.uiSrcPath')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'javaSrcPath')} checked={columns.javaSrcPath?.list}  />}
                	label={t('resources.tenant.fields.javaSrcPath')}
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