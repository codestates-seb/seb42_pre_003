import styled from 'styled-components';
import AnsComment from './AnsComment';
import AnsInfo from './AnsInfo';
import {
	CountUpIcon,
	CountDownIcon,
	BookMarkIcon,
	BookFullIcon,
	WillbeDelete,
} from './AnsIcon';
import useAnsStore from '../../store/ansStore';

const BREAK_POINT_MO = 576;

const ConWrap = styled.div`
	display: flex;
	margin-top: 1rem;
	padding-bottom: 1rem;
	border-bottom: ${(props) =>
		props.type === 'question' ? null : '1px solid #e3e6e8'};
`;

const AnsSide = styled.div`
	display: flex;
	padding-right: 0.8rem;
	flex-direction: column;
	align-items: center;
	font-weight: 600;
	color: #6a737c;
	svg {
		fill: hsl(210, 8%, 75%);
		cursor: pointer;
	}
	svg.iconBookmark {
		fill: #00abbb;
	}
`;

const AnsBox = styled.div`
	p {
		padding-bottom: 1rem;
		font-size: 0.75rem;
		font-weight: 500;
		line-height: 1.5;
		white-space: pre-line;
	}
`;

const AnsTag = styled.div`
	margin-bottom: 2rem;
	ul {
		display: flex;
		gap: 0.4rem;
		font-size: 0.55rem;
		font-weight: 500;
		@media only screen and (max-width: ${BREAK_POINT_MO}px) {
			flex-wrap: wrap;
		}
		li {
			padding: 0.25rem;
			border-radius: 0.188rem;
			background: rgba(0, 0, 0, 0.5);
			color: #fff;
		}
	}
`;

function AnsCon({ type }) {
	const { vote, plusVote, minusVote, book, handleBook } = useAnsStore();

	return (
		<ConWrap type={type}>
			<AnsSide>
				<CountUpIcon onClick={plusVote} />
				{vote}
				<CountDownIcon onClick={minusVote} />
				{book ? (
					<BookFullIcon onClick={handleBook} />
				) : (
					<BookMarkIcon onClick={handleBook} />
				)}
			</AnsSide>
			<AnsBox>
				<WillbeDelete />
				{type === 'question' ? (
					<AnsTag>
						<ul>
							{['javascript', 'node.js', 'mongodb', 'express', 'mongoose'].map(
								(el, idx) => (
									<li key={idx}>{el}</li>
								),
							)}
						</ul>
					</AnsTag>
				) : null}
				<AnsInfo />
				<AnsComment />
			</AnsBox>
		</ConWrap>
	);
}

export default AnsCon;
