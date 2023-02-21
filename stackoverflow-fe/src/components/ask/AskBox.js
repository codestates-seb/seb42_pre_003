import styled from 'styled-components';
import AskInput from './AskInput';
import AskGuide from './AskGuide';

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
	type,
	inputLabel,
	inputText,
	values,
	guideTitle,
	guideDes,
	...rest
}) {
	const getAble = localStorage.getItem('able');
	const able = JSON.parse(getAble);

	return (
		<AskBoxWrap able={able[name]}>
			<AskInput
				type={type}
				label={inputLabel}
				text={inputText}
				values={values}
				able={able[name]}
				{...rest}
			/>
			<AskGuide able={able[name]} title={guideTitle} description={guideDes} />
		</AskBoxWrap>
	);
}

export default AskBox;
