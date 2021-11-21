import { fetchUtils } from 'react-admin';
import { stringify } from 'query-string';
import AxiosInstance from '../../../AxiosInstance';
import moment from 'moment'

const apiUrl = '/api/god';
const httpClient = fetchUtils.fetchJson;

export default {
    getList: (resource, params) => {
        const { page, perPage } = params.pagination;
        const { field, order } = params.sort;
        console.log("user")

        const q = {
            page: page - 1,
            size: perPage,
            "query": params.filter
        }
        
        const newFilter = {}
        Object.keys(q.query).forEach(k => {
			if(q.query[k] !== undefined) {
				if (q.query[k]===false) {
					newFilter[k] = {$in : [q.query[k], null]}
				}
				else {
            		
            		newFilter[k] = q.query[k]
            	}
            }
        })
        q.query = newFilter
        
        if(q.query.title) {
        q.query.title={$regex: '.*' + q.query.title + '.*', $options: 'i'}
        }
        
        if(q.query.className) {
        q.query.className={$regex: '.*' + q.query.className + '.*', $options: 'i'}
        }
        
        if(q.query.description) {
        q.query.description={$regex: '.*' + q.query.description + '.*', $options: 'i'}
        }
        
        if(q.query.name) {
        q.query.name={$regex: '.*' + q.query.name + '.*', $options: 'i'}
        }
        
        if(q.query.packageName) {
        q.query.packageName={$regex: '.*' + q.query.packageName + '.*', $options: 'i'}
        }
        
        if(q.query.tenant) {
        q.query.tenant={$regex: '.*' + q.query.tenant + '.*', $options: 'i'}
        }
        
        
        

        return AxiosInstance.post(apiUrl + "/" + resource + "/q" , q).then((response) => ({
            data: response.data.content.map(d => {d.id=d.uid; return d}),
            total: response.data.totalElements
        }))
    },

    getOne: (resource, params) =>
        AxiosInstance.get(apiUrl + "/" + resource + "/" + params.id).then((response) => ({
            data: response.data,
        })),

    getMany: (resource, params) => {
        console.log(params)
        console.log("get many")
        const q = {
            page: 0,
            size: 100,
            "query": {}
        }

        return AxiosInstance.post(apiUrl + "/" + resource + "/q", q)
        .then((response) => ({
            data: response.data.content.map(d => {d.id=d.uid; return d}),
            total: response.data.totalElements
        }))
    },

    getManyReference: (resource, params) => {
        const { page, perPage } = params.pagination;
        const { field, order } = params.sort;

        const q = {
            page: page - 1,
            size: perPage,
            "query": params.filter
        }

        return AxiosInstance.post(apiUrl + "/" + resource + "/q", q)
        .then((response) => ({
            data: response.data.content.map(d => {d.id=d.uid; return d}),
            total: response.data.totalElements
        }))
    },

    update: (resource, params) => {
        return AxiosInstance.put(apiUrl + "/" + resource + "/" + params.id, params.data)
            .then((response) => ({ data: response.data }))
    },

    updateMany: (resource, params) => {
        const query = {
            filter: JSON.stringify({ id: params.ids}),
        };
        return AxiosInstance.put(apiUrl + "/" + resource + "/" + stringify(query), params.data)
            .then(({ json }) => ({ data: json }));
    },

    create: (resource, params) => {
        console.log(params.data)
       
        console.log(params.data)
        return AxiosInstance.post(apiUrl + "/" + resource, params.data)
        .then((response) => ({
            data: { ...params.data, id: response.data.uid },
        }))
    },

    delete: (resource, params) =>
        AxiosInstance.delete(apiUrl + "/" + resource + "/" + params.id)
            .then((response) => ({ data: response.data })),

    deleteMany: (resource, params) => {
        const query = {
            filter: JSON.stringify({ id: params.ids}),
        };
        return AxiosInstance.delete(apiUrl + "/" + resource + "/" + params.ids)
        	.then(({  }) => ({ data: [] }));
    }
};