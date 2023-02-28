import AnsInput from './AnsInput';
import AnsEditor from './AnsEditor';
import styled from 'styled-components';
import useAnsStore from '../../store/ansStore';
import { useBoxStore } from '../../store/askStore';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const InputBtnBox = styled.div`
	display: flex;
	gap: 0.35rem;
	align-items: center;
	button {
		margin-top: 0.8rem;
		padding: 0.45rem 0.5rem;
		font-size: 0.65rem;
		border-radius: 0.188rem;
		border: 1px solid #d7d8d9;
		&:last-child {
			margin-left: auto;
		}
	}
`;

const InputButton = styled.button`
	margin-top: 0.8rem;
	padding: 0.45rem 0.5rem;
	color: #fff;
	font-size: 0.6rem;
	font-weight: 500;
	border-radius: 0.188rem;
	background: hsl(206, 100%, 52%);
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4);
	cursor: ${(props) => (props.disabled ? 'not-allowed' : 'cursor')};
`;

const ConTitle = styled.h4`
	margin: 1rem 0 0.75rem;
	font-weight: 500;
`;

function AnsEdit() {
	let { id } = useParams();
	const { editData } = useBoxStore();
	const { edTitleBind, edBodyBind, edTagBind } = useAnsStore();
	const { editAnswer, delAnswer, editDown, delDown } = useAnsStore();
	const { edTitle, edBody } = useAnsStore();
	const { handlePage } = useAnsStore();
	const navigate = useNavigate();

	const handleEdit = (e) => {
		e.preventDefault();

		const item = {
			questionTitle: edTitle,
			questionContent: edBody,
		};

		console.log(item);
		editAnswer(`${process.env.REACT_APP_API_URL}/questions/${id}`, item);
		setTimeout(() => {
			window.location.reload();
		}, 300);
	};

	const handleEditDown = (e) => {
		e.preventDefault();

		const item = {
			answerContent: edBody,
		};

		console.log(item);
		editDown(`${process.env.REACT_APP_API_URL}/answers/${id}`, item);
		// setTimeout(() => {
		// 	window.location.reload();
		// }, 300);
	};

	const handleDel = (e) => {
		e.preventDefault();

		delAnswer(`${process.env.REACT_APP_API_URL}/questions/${id}`);
		setTimeout(() => {
			navigate(`/`);
		}, 300);
	};

	const handleDelDown = (e) => {
		e.preventDefault();

		delDown(`${process.env.REACT_APP_API_URL}/answers/${editData[0].id}`);
		setTimeout(() => {
			window.location.reload();
		}, 300);
	};

	const handleClose = (e) => {
		e.preventDefault();

		handlePage('read');
	};

	return (
		<>
			<ConTitle>Title</ConTitle>
			<AnsInput value={edTitle} func={edTitleBind} />
			<ConTitle>Body</ConTitle>
			<AnsEditor value={edBody} func={edBodyBind} />
			<ConTitle>Tags</ConTitle>
			<AnsInput func={edTagBind} />
			<InputBtnBox>
				<InputButton
					onClick={
						editData[0].name === 'question' ? handleEdit : handleEditDown
					}
				>
					Save Edits
				</InputButton>
				<button
					onClick={editData[0].name === 'question' ? handleDel : handleDelDown}
				>
					Delete
				</button>
				<button onClick={handleClose}>Close</button>
			</InputBtnBox>
		</>
	);
}

export default AnsEdit;
