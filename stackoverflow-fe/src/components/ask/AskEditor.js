import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AskEditor({ able, value, func }) {
	return (
		<div>
			<ReactQuill
				readOnly={!able}
				value={value}
				onChange={(value) => func(value)}
			/>

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AskEditor;
