import React, { useEffect, useState } from "react";
import AxiosInstance from "../../../../AxiosInstance";
import { useFormState, useForm } from 'react-final-form';
import SparkMD5 from "spark-md5";
import { FileField, FileInput, Link } from "ra-ui-materialui";
import RecordPlayer from './RecordPlayer'
const Record = props => {
      const { values } = useFormState()
      const form = useForm()
        const [files, setFiles] = useState([])

        useEffect(async () => {
            console.log(values)
            if (values.record) {
	            for(let i =0; i<values.record.length; i++) {
	              const v = values.record[i]
	              const downloadBody = await download(v)
	              const file = {
	                info: downloadBody.info
	              }
	              files.push(file)
	            }
	           
	            setFiles([...files])
            }
        },[])


        const upload = async (file) => {
            console.log(file)
            var formData = new FormData()
            formData.append('file', file, file.path)
    
            const response = await AxiosInstance.post("/api/file/upload", formData, {
                headers: { 'content-type': 'multipart/form-data', 'Accept': '*/*'}
            });
            return response.data;
        }

        const download = async (fileId) => {
          
            // const response = await AxiosInstance.get("/api/file/download/" + fileId, {
            //     responseType: 'arraybuffer',
            //     headers:{
            //         "Accept": "*/*",
            //         'Content-Type': 'application/binary'
            //     }
            // });
            // console.log(response)
            const info = await AxiosInstance.get("/api/file/info/" + fileId, {
              headers:{
                  "Accept": "*/*",
                  'Content-Type': 'application/json'
              }
            });
            return {
              // data: response.data,
              info: info.data
            }
        }

      const onDrop = React.useCallback(async acceptedFiles => {
        console.log(acceptedFiles[0])
        const fileId = await upload(acceptedFiles[0])
        //files.push({info:{fileId: fileId, }})
        acceptedFiles[0].fileId=fileId
        setFiles([...files])
        form.change('record', [...files.map(file=>file.info.fileId), fileId])

      }, [])

      const onRemove = React.useCallback(removedFile => {
        console.log(removedFile)
        const updatedList = files.filter(f => f.info.fileId!=removedFile.rawFile.fileId)
        setFiles([...updatedList])
        form.change('record', updatedList)

        
      }, [])
    return <div>
      
      {files.map(file => {
        console.log(file)
        return <span><br/>
         <RecordPlayer url={process.env.REACT_APP_SERVER_URL + '/api/video/' + file.info.fileId} />
        
        </span>
      })}
      <FileInput
            {...props}
            source="_record"
            label="Record"
            multiple={true}
            options={{onDrop: onDrop, onRemove: onRemove}}
            >
            <FileField {...props} source="src" title="title" target="_blank" src="url" />
            
        </FileInput>
        </div>
}

export default Record