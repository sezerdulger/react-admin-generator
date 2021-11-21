import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const fieldShowColumnSlicer = createSlice({
  name: 'fieldShowColumnSlicer',
  initialState,
  reducers: {
    show(state, action) {
        //console.log(action)
        //console.log(state)
        Object.keys(action.payload).forEach(c => {
          //console.log(c)
          state[c] = action.payload[c]
        })
      //console.log(state)
    },
  },
})

export const { show } = fieldShowColumnSlicer.actions
export default fieldShowColumnSlicer.reducer