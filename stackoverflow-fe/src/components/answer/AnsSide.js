import styled from 'styled-components';
import {
	CountUpIcon,
	CountDownIcon,
	BookMarkIcon,
	BookFullIcon,
} from './AnsIcon';

const SideWrap = styled.div`
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

function AnsSide({ vote, plusVote, minusVote, book, handleBook }) {
	return (
		<SideWrap>
			<div onClick={plusVote}>
				<CountUpIcon />
			</div>
			{vote}
			<div onClick={minusVote}>
				<CountDownIcon />
			</div>
			<div onClick={handleBook}>
				{book ? <BookFullIcon /> : <BookMarkIcon />}
			</div>
		</SideWrap>
	);
}

export default AnsSide;
