import { createSlice } from '@reduxjs/toolkit'

const initialState = {  }

const musiclessonShowColumnSlicer = createSlice({
  name: 'musiclessonShowColumnSlicer',
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

export const { show } = musiclessonShowColumnSlicer.actions
export default musiclessonShowColumnSlicer.reducer