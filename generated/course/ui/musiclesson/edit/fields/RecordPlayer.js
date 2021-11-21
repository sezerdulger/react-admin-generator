import { Player, Video, DefaultUi, usePlayerContext } from '@vime/react';
import TapSidesToSeek from '../../../../TapSidesToSeek';
import React, { useEffect, useRef } from 'react';
import '@vime/core/themes/default.css';
import { useForm, useFormState } from 'react-final-form'

const RecordPlayer = props => {
	const { values } = useFormState()

  	const player = useRef(null);

  	const onPlaybackReady = () => {
  	};

  	const [currentTime] = usePlayerContext(player, 'currentTime', 0);

  	useEffect(() => {
    	console.log(currentTime);
  	}, [currentTime]);
  	
    return (
      <div id="container">
        <Player playsinline ref={player} onVmPlaybackReady={onPlaybackReady}>
          <Video poster="">
            <source
              //data-src={values.Record}
              data-src={props.url}
              type="video/mp4"
             />
          </Video>

          <DefaultUi>
            {/* Custom UI Component. */}
            <TapSidesToSeek />
          </DefaultUi>
        </Player>
      </div>
    );
}

export default RecordPlayer