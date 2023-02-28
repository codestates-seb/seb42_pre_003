import React from 'react';
import ReactQuill from 'react-quill';
import '../../style/quill-custom.css';

function Editor({ able, value, func, name }) {
	const modules = {
		toolbar: [
			[{ size: [] }],
			['bold', 'italic', 'underline', 'strike', 'blockquote'],
			[
				{ list: 'ordered' },
				{ list: 'bullet' },
				{ indent: '-1' },
				{ indent: '+1' },
			],
			['link', 'image', 'video'],
			['clean'],
		],
		clipboard: {
			// toggle to add extra line breaks when pasting HTML:
			matchVisual: false,
		},
	};
	const formats = [
		'size',
		'bold',
		'italic',
		'underline',
		'strike',
		'blockquote',
		'list',
		'bullet',
		'indent',
		'link',
		'image',
		'video',
	];

	return (
		<div>
			<ReactQuill
				modules={modules}
				formats={formats}
				theme='snow'
				value={value}
			/>
		</div>
	);
}

export default Editor;
