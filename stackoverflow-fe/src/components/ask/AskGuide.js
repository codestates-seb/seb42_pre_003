import { WriteIcon, BellIcon } from './AskIcon';
import styled from 'styled-components';

const GuideBox = styled.div`
	width: 30%;
	margin-left: 0.85rem;
	border: 1px solid hsl(210, 8%, 85%);
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), 0 1px 4px rgba(0, 0, 0, 0.05),
		0 2px 8px rgba(0, 0, 0, 0.05);
	border-radius: 0.188rem;
	strong {
		display: block;
		padding: 0.55rem;
		font-size: 0.8rem;
		font-weight: 500;
		border-bottom: 1px solid hsl(210, 8%, 85%);
	}
`;

const GuideCon = styled.div`
	display: flex;
	padding: 0.65rem;
	font-size: 0.65rem;
	svg {
		width: 20%;
	}
	span {
		display: block;
		width: 80%;
	}
`;

function AskGuide({ title, description, type }) {
	return (
		<GuideBox>
			<strong>{title}</strong>
			<GuideCon>
				{type === 'selector' ? <BellIcon /> : <WriteIcon />}
				<span>{description}</span>
			</GuideCon>
		</GuideBox>
	);
}
export default AskGuide;
