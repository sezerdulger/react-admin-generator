import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const fieldFieldEditedSlicer = createSlice({
  name: 'fieldFieldEditedSlicer',
  initialState,
  reducers: {
    edited(state, action) {
      state[action.payload.field] = action.payload.value
      //console.log(state)
    },
  },
})

export const { edited } = fieldFieldEditedSlicer.actions
export default fieldFieldEditedSlicer.reducer