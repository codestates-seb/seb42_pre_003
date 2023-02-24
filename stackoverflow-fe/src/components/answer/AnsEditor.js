import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AnsEditor({ func }) {
	return (
		<div>
			<ReactQuill onChange={(value) => func(value)} />

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AnsEditor;
