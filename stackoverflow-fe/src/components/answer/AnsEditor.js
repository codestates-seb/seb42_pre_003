import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import '../../style/quill-custom.css';

function AnsEditor({ value, func }) {
	const modules = {
		toolbar: [
			[{ header: '1' }, { header: '2' }, { font: [] }],
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
			matchVisual: false,
		},
	};
	const formats = [
		'header',
		'font',
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
				value={value === '' ? '' : value}
				onChange={(value) => func(value)}
			/>
		</div>
	);
}

export default AnsEditor;
