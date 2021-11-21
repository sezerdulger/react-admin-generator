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
import { show } from "./slicer/musiclessonShowColumnSlicer";
import AxiosInstance from "../../AxiosInstance";
import {musiclessonExt, MusicLessonListCustomButtons} from "./ext/musiclessonExt";
import { useFormState, useForm } from 'react-final-form';

import Title from "./edit/fields/Title";
import Record from "./edit/fields/Record";
import Note from "./edit/fields/Note";

const columnStates = {
		title: {list: true},
		record: {list: true},
		note: {list: true},
    }
    
export const MusicLessonList = (props) => {
    const isSmall = useMediaQuery(theme => theme.breakpoints.down('sm'));
    
    const [columns, setColumns]=React.useState({})
    
    const musiclessonShowColumnSlicer = useSelector(
        (state) => state.musiclessonShowColumnSlicer
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
		console.log(musiclessonShowColumnSlicer)
        setColumns({...musiclessonShowColumnSlicer})
    }, [musiclessonShowColumnSlicer])
    
    return (
        <List {...props} filters={<MusicLessonFilter />} actions={<ListActions />}>
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
                    
                    {columns.record?.list &&
                    <TextField source="record" />
                    }
                    
                    {columns.note?.list &&
                    <TextField source="note" />
                    }
                    <EditButton />
                    <MusicLessonListCustomButtons />
                </Datagrid>
            )}
        </List>
    );
}

export const MusicLessonEdit = props => (
    <Edit {...props} mutationMode='optimistic'>
        <SimpleForm>
            <Title {...props} formAction="edit" />
            <Record {...props} formAction="edit" />
            <Note {...props} formAction="edit" />
        </SimpleForm>
    </Edit>
);

export const MusicLessonCreate = props => (
    <Create {...props} mutationMode='optimistic'>
        <SimpleForm>
	        <Title {...props} formAction="create" />
	        <Record {...props} formAction="create" />
	        <Note {...props} formAction="create" />
            <MusicLessonCreateForm {...props}/>
        </SimpleForm>
    </Create>
);

const MusicLessonCreateForm = props => {
    const { values } = useFormState()
    const form = useForm()


    React.useEffect(() => {
        console.log(values)
        musiclessonExt.edited(values, form)
    }, [values])

    return (<div></div>)
}

const MusicLessonFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Title" source="title" allowEmpty />
    	<ArrayInput source="record" label="Record">
            <SimpleFormIterator>
                <TextInput  />
            </SimpleFormIterator>
        </ArrayInput>
        <TextInput label="Note" source="note" allowEmpty />
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
        const sessionColumns = sessionStorage.getItem("course_musiclesson_columns")

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
		sessionStorage.setItem("course_musiclesson_columns", JSON.stringify({...columns, [columnId]: v}))
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
                	label={t('resources.musiclesson.fields.title')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'record')} checked={columns.record?.list}  />}
                	label={t('resources.musiclesson.fields.record')}
                />
                </MenuItem>
                <MenuItem>
                <FormControlLabel
					control={
                    <Checkbox onChange={e => showColumn(e, 'note')} checked={columns.note?.list}  />}
                	label={t('resources.musiclesson.fields.note')}
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