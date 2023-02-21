import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AskEditor({ values }) {
	return (
		<div>
			<ReactQuill {...values} />

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AskEditor;
