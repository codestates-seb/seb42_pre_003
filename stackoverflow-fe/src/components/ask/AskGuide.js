import { WriteIcon, BellIcon } from './AskIcon';
import styled from 'styled-components';

const BREAK_POINT_PC = 1100;

const GuideBox = styled.div`
	display: ${(props) => (props.able ? 'none' : 'block')};
	width: 17rem;
	margin-left: 0.85rem;
	border: 1px solid hsl(210, 8%, 85%);
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), 0 1px 4px rgba(0, 0, 0, 0.05),
		0 2px 8px rgba(0, 0, 0, 0.05);
	border-radius: 0.188rem;
	strong {
		display: block;
		padding: 0.55rem;
		font-size: 0.73rem;
		font-weight: 500;
		border-bottom: 1px solid hsl(210, 8%, 85%);
	}
	@media only screen and (max-width: ${BREAK_POINT_PC}px) {
		width: 100%;
		margin-top: 0.85rem;
		margin-left: 0;
	}
`;

const GuideCon = styled.div`
	display: flex;
	padding: 0.65rem;
	font-size: 0.63rem;
	font-weight: 500;
	svg {
		max-width: 3rem;
	}
	span {
		display: block;
		width: calc(100% - 4rem);
		margin-left: 1rem;
		line-height: 1.1;
		white-space: pre-line;
	}
`;

function AskGuide({ act, title, description, type }) {
	return (
		<GuideBox able={!act}>
			<strong>{title}</strong>
			<GuideCon>
				{type === 'selector' ? <BellIcon /> : <WriteIcon />}
				<span>{description}</span>
			</GuideCon>
		</GuideBox>
	);
}
export default AskGuide;
