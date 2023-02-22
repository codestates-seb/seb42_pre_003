import styled from 'styled-components';
import { CountUpIcon, CountDownIcon, BookMarkIcon } from './AnsIcon';

const SideWrap = styled.div`
	display: flex;
	padding-right: 0.8rem;
	flex-direction: column;
	align-items: center;
	font-weight: 600;
	color: #6a737c;
	svg {
		fill: hsl(210, 8%, 75%);
	}
`;

function AnsSide() {
	return (
		<SideWrap>
			<CountUpIcon />2<CountDownIcon />
			<BookMarkIcon />
		</SideWrap>
	);
}

export default AnsSide;
