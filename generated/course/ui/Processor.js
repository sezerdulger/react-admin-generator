// in src/Processor.js
import * as React from "react";
import { Admin, Resource, ListGuesser, EditGuesser } from 'react-admin';
import { MusicLessonList, MusicLessonCreate, MusicLessonEdit } from './musiclesson/musiclesson';

import jsonServerProvider from 'ra-data-json-server';
import PostIcon from '@material-ui/icons/Book';
import UserIcon from '@material-ui/icons/Group';
import ComputerIcon from '@material-ui/icons/Computer';
import Dashboard from '../Dashboard';
import authProvider from './authProvider';
import customDataProvider from './customDataProvider';
import multipleDataProvider from "./multipleDataProvider";
import { Provider } from 'react-redux'

import musiclessonShowColumnSlicer from "./musiclesson/slicer/musiclessonShowColumnSlicer";
import musiclessonFieldEditedSlicer from "./musiclesson/slicer/musiclessonFieldEditedSlicer";


import { i18nProvider } from "./i18nprovider";
import MyLayout from "./main/MyLayout";


const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');
const Processor = () => (
  //<Provider store={store}>
    <Admin
      layout={MyLayout}
      dashboard={Dashboard} 
      dataProvider={multipleDataProvider} 
      authProvider={authProvider}
      i18nProvider={i18nProvider}
      customReducers={{
	musiclessonShowColumnSlicer,
	musiclessonFieldEditedSlicer,
	}}>
    {/* <Resource name="posts" list={PostList}  edit={PostEdit} create={PostCreate} icon={PostIcon} /> */}
    <Resource match={{id: "uid"}} 
      name="musiclesson" 
      list={MusicLessonList} 
      edit={MusicLessonEdit} 
      create={MusicLessonCreate} 
      icon={UserIcon} />
    </Admin>
    //</Provider>
);
export default Processor;