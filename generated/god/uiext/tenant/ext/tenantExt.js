
class TenantExt {
    constructor() {
        
    }

    edited = (values, form ) => {
        console.log(values)
    }
}

export const tenantExt= new TenantExt

export const TenantListCustomButtons = props => {
    return (
        <div></div>
    )
}

export default {tenantExt, TenantListCustomButtons}