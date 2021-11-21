
class MusicLessonExt {
    constructor() {
        
    }

    edited = (values, form ) => {
        console.log(values)
    }
}

export const musiclessonExt= new MusicLessonExt

export const MusicLessonListCustomButtons = props => {
    return (
        <div></div>
    )
}

export default {musiclessonExt, MusicLessonListCustomButtons}