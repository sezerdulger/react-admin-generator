import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const entityFieldEditedSlicer = createSlice({
  name: 'entityFieldEditedSlicer',
  initialState,
  reducers: {
    edited(state, action) {
      state[action.payload.field] = action.payload.value
      //console.log(state)
    },
  },
})

export const { edited } = entityFieldEditedSlicer.actions
export default entityFieldEditedSlicer.reducer