import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const musiclessonFieldEditedSlicer = createSlice({
  name: 'musiclessonFieldEditedSlicer',
  initialState,
  reducers: {
    edited(state, action) {
      state[action.payload.field] = action.payload.value
      //console.log(state)
    },
  },
})

export const { edited } = musiclessonFieldEditedSlicer.actions
export default musiclessonFieldEditedSlicer.reducer