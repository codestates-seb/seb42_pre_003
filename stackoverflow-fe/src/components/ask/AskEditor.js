import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AskEditor({ able, values }) {
	return (
		<div>
			<ReactQuill readOnly={!able} {...values} />

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AskEditor;
