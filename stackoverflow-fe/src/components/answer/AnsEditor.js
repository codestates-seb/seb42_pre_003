import useAnsStore from '../../store/ansStore';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

function AnsEditor() {
	const { answerBind } = useAnsStore();
	return (
		<div>
			<ReactQuill onChange={(value) => answerBind(value)} />

			{/* <div dangerouslySetInnerHTML={{ __html: content }} /> */}
		</div>
	);
}

export default AnsEditor;
