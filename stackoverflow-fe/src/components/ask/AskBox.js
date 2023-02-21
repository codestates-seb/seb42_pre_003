import styled from 'styled-components';
import AskInput from './AskInput';
import AskGuide from './AskGuide';

const BREAK_POINT_PC = 1100;

const AskBoxWrap = styled.div`
	display: flex;
	margin-top: 0.65rem;
	align-items: flex-start;
	@media only screen and (max-width: ${BREAK_POINT_PC}px) {
		flex-direction: column;
	}
`;

function AskBox({
	type,
	inputLabel,
	inputText,
	values,
	guideTitle,
	guideDes,
	...rest
}) {
	return (
		<AskBoxWrap>
			<AskInput
				type={type}
				label={inputLabel}
				text={inputText}
				values={values}
				{...rest}
			/>
			<AskGuide title={guideTitle} description={guideDes} />
		</AskBoxWrap>
	);
}

export default AskBox;
