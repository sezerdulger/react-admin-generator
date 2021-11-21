
class FieldExt {
    constructor() {
        
    }

    edited = (values, form ) => {
        console.log(values)
    }
}

export const fieldExt= new FieldExt

export const FieldListCustomButtons = props => {
    return (
        <div></div>
    )
}

export default {fieldExt, FieldListCustomButtons}