import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const tenantFieldEditedSlicer = createSlice({
  name: 'tenantFieldEditedSlicer',
  initialState,
  reducers: {
    edited(state, action) {
      state[action.payload.field] = action.payload.value
      //console.log(state)
    },
  },
})

export const { edited } = tenantFieldEditedSlicer.actions
export default tenantFieldEditedSlicer.reducer