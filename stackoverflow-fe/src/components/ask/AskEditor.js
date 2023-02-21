import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { useState } from 'react';

function AskEditor() {
	const [content, setCon] = useState('');
	const handleChange = (con) => {
		setCon(con);
	};

	return (
		<div>
			<ReactQuill onChange={handleChange} />

			<div dangerouslySetInnerHTML={{ __html: content }} />
		</div>
	);
}

export default AskEditor;
