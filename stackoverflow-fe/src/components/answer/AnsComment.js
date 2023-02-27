import styled from 'styled-components';
import { useState } from 'react';
import AnsInput from './AnsInput';
import useAnsStore from '../../store/ansStore';

const CommentWrap = styled.div`
	margin-top: 1.5rem;
	border-top: 1px solid #e3e6e8;
`;

const CommentBox = styled.ul`
	display: flex;
	padding: 0.5rem 1.3rem;
	flex-direction: column;
	gap: 0.8rem;
`;

const CommentItem = styled.li`
	font-size: 0.65rem;
	font-weight: 500;
	line-height: 1.2;
	* {
		display: inline-block;
	}
	span {
		position: relative;
		margin: 0 0.3rem 0 0.8rem;
		padding: 0.12rem 0.2rem;
		color: hsl(206, 100%, 40%);
		background: ${(props) => (props.own ? '#d9e9f7' : null)};
		border-radius: 0.188rem;
		&::before {
			position: absolute;
			top: 0.43rem;
			left: -0.5rem;
			width: 7px;
			height: 1.4px;
			background: #000;
			content: '';
		}
	}
	em {
		color: hsl(210, 8%, 60%);
	}
`;

const CommentButton = styled.button`
	margin-bottom: 0.5rem;
	font-size: 0.65rem;
	font-weight: 500;
	line-height: 1.2;
	color: hsl(210, 8%, 55%);
	opacity: 0.6;
`;

const Comment = styled.div`
	display: flex;
	gap: 0.2rem;
`;

const AddButton = styled.button`
	padding: 0.35rem 0.5rem;
	color: #fff;
	font-size: 0.6rem;
	font-weight: 500;
	border-radius: 0.188rem;
	background: hsl(206, 100%, 52%);
	box-shadow: inset 0 0.08rem 0 0 hsla(0, 0%, 100%, 0.4);
`;

const CommentEdit = styled.ul`
	display: flex;
	gap: 0.3rem;
	li {
		font-size: 0.55rem;
		color: #62666c;
		cursor: pointer;
	}
`;

function AnsComment({ QaCom }) {
	const [com, setCom] = useState(false);
	const [ed, setEd] = useState(
		QaCom ? Array.from({ length: QaCom.length }).fill(false) : [],
	);
	const { comment, comBind, comReset, addCom, editCom, delCom } = useAnsStore();

	const handleActive = () => {
		setCom(!com);
	};

	const handleEd = (num) => {
		const array = [...ed];

		setEd(
			array.map((el, idx) => {
				if (idx === num) {
					return !el;
				} else {
					return el;
				}
			}),
		);
	};

	const handleComment = (e) => {
		e.preventDefault();

		const item = {
			commentContent: comment,
			questionId: 1,
			answerId: 1,
		};

		addCom(
			`${process.env.REACT_APP_API_URL}/comments?_csrf=6614bf1f-7bb5-43c4-be85-e5ea4fa088ee`,
			item,
		);
		console.log(comment);
		comReset();
	};

	const handleEdit = (e) => {
		e.preventDefault();

		const item = {
			commentContent: comment,
		};

		editCom(
			`${process.env.REACT_APP_API_URL}/comments/1?_csrf=69bf413f-704a-4ecf-8e65-2e88619606f5`,
			item,
		);
		console.log(comment);
		comReset();
	};

	const handleDel = (e) => {
		e.preventDefault();

		delCom(
			`${process.env.REACT_APP_API_URL}/comments/1?_csrf=4b30564f-3ca8-4ef3-bfa2-a6931eb92c77`,
		);
	};

	return (
		<CommentWrap>
			<CommentBox>
				{QaCom &&
					QaCom.map((el, idx) => (
						<CommentItem key={QaCom.questionId || idx}>
							{el.commentContent}
							<span>{el.memberName}</span>
							<em>{el.createdAt || 'Feb 16 at 13:45'}</em>
							<CommentEdit>
								<li onClick={() => handleEd(idx)}>Edit</li>
								<li onClick={handleDel}>Delete</li>
							</CommentEdit>
							{ed[idx] ? (
								<Comment>
									<AnsInput func={comBind} />
									<AddButton onClick={handleEdit}>Enter</AddButton>
								</Comment>
							) : null}
						</CommentItem>
					))}
				{/* <CommentItem>
					Could you please share one of your error handlers? Are these handlers
					accept three parameters like error, doc, next?
					<span>Mostafa Fakhraei</span>
					<em>Feb 16 at 13:45</em>
				</CommentItem>
				<CommentItem own>
					@MostafaFakhraei I edit my question to include the error handler
					function. Indeed it uses the three arguments mentioned.
					<span>Ayoub k</span>
					<em>Feb 17 at 0:14</em>
				</CommentItem> */}
			</CommentBox>
			<CommentButton onClick={handleActive}>Add a comment</CommentButton>
			{com ? (
				<Comment>
					<AnsInput func={comBind} />
					<AddButton onClick={handleComment}>Enter</AddButton>
				</Comment>
			) : null}
		</CommentWrap>
	);
}

export default AnsComment;
