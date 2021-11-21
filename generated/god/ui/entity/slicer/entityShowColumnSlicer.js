import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const entityShowColumnSlicer = createSlice({
  name: 'entityShowColumnSlicer',
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

export const { show } = entityShowColumnSlicer.actions
export default entityShowColumnSlicer.reducer