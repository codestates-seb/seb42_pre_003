import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AskEditor({ able, func }) {
	return (
		<div>
			<ReactQuill readOnly={!able} onChange={(value) => func(value)} />

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AskEditor;
