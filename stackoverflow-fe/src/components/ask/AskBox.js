import styled from 'styled-components';
import AskText from './AskText';
import AskGuide from './AskGuide';
import { useBoxStore } from '../../store/askStore';

const BREAK_POINT_PC = 1100;

const AskBoxWrap = styled.div`
	display: flex;
	margin-top: 0.65rem;
	align-items: flex-start;
	opacity: ${(props) => (props.able ? 1 : 0.3)};
	@media only screen and (max-width: ${BREAK_POINT_PC}px) {
		flex-direction: column;
	}
`;

function AskBox({
	name,
	value,
	type,
	inputLabel,
	inputText,
	func,
	guideTitle,
	guideDes,
	review,
	...rest
}) {
	const { ableData, actData } = useBoxStore();
	const base =
		ableData[0][name] || ableData[0][name] === undefined ? true : false;
	const act =
		actData[0][name] || ableData[0][name] === undefined ? true : false;

	console.log(actData[0]);

	return (
		<AskBoxWrap able={base}>
			<AskText
				name={name}
				value={value}
				type={type}
				label={inputLabel}
				text={inputText}
				func={func}
				able={base}
				review={review}
				{...rest}
			/>
			<AskGuide act={act} title={guideTitle} description={guideDes} />
		</AskBoxWrap>
	);
}

export default AskBox;
