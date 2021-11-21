
class EntityExt {
    constructor() {
        
    }

    edited = (values, form ) => {
        console.log(values)
    }
}

export const entityExt= new EntityExt

export const EntityListCustomButtons = props => {
    return (
        <div></div>
    )
}

export default {entityExt, EntityListCustomButtons}