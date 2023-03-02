import styled from 'styled-components';
import useAnsStore from '../../store/ansStore';
import moment from 'moment';
import profile from '../../img/profile.png';

const BREAK_POINT_MO = 576;

const InfoWrap = styled.div`
	display: flex;
	padding-top: 0.3rem;
	align-items: flex-start;
	justify-content: space-between;
	color: hsl(206, 100%, 40%);
	font-weight: 600;
	font-size: 0.6rem;
	@media only screen and (max-width: ${BREAK_POINT_MO}px) {
		flex-direction: column;
		gap: 0.8rem;
	}
`;

const InfoButton = styled.button`
	color: #6a737c;
	font-weight: 500;
	font-size: 0.65rem;
`;

const InfoUser = styled.div`
	margin-top: -0.3rem;
	padding: 0.3rem;
	color: #6a737c;
	font-weight: 600;
	font-size: 0.6rem;
	background: #d9e9f7;
	border-radius: 0.188rem;
	@media only screen and (max-width: ${BREAK_POINT_MO}px) {
		margin-top: 0;
	}
`;

const InfoAva = styled.div`
	display: flex;
	margin-top: 0.3rem;
	align-items: center;
	img {
		max-width: 1.2rem;
		max-height: 1.2rem;
		border-radius: 0.133rem;
	}
	strong {
		display: block;
		padding: 0.33rem;
		color: hsl(206, 100%, 40%);
		font-weight: 600;
		font-size: 0.6rem;
	}
`;

function AnsInfo({ data }) {
	const { handlePage } = useAnsStore();
	const { edTitleBind, edBodyBind, edModeBind, edIdBind } = useAnsStore();

	const addItemData = (e) => {
		e.preventDefault();

		if (data.answerId) {
			const { answerId, answerContent } = data;

			edBodyBind(answerContent);
			edIdBind(answerId);
			edModeBind('answer');
		} else {
			const { questionId, questionTitle, questionContent } = data;

			edTitleBind(questionTitle);
			edBodyBind(questionContent);
			edIdBind(questionId);
			edModeBind('question');
		}
		handlePage('edit');
	};

	const createTime = moment(data.createdAt).fromNow();
	const editTime = moment(data.modifiedAt).fromNow();

	return (
		<InfoWrap>
			<InfoButton onClick={addItemData}>Edit</InfoButton>
			<span>{`edited ${createTime}`}</span>
			<InfoUser>
				<span>{`asked ${editTime}`}</span>
				<InfoAva>
					<img src={profile} alt='아바타' />
					<strong>JMC</strong>
				</InfoAva>
			</InfoUser>
		</InfoWrap>
	);
}

export default AnsInfo;
