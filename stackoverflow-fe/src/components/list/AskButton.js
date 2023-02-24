import React from 'react';
import styled from 'styled-components';

const Section = styled.section`
	button {
		width: 105px;
		height: 37px;
		color: #fff;
		background-color: hsl(206deg 100% 52%);
		border-radius: 3px;
		font-size: 13px;
		margin-bottom: 20px;
	}

	button:hover {
		background-color: hsl(206deg 100% 40%);
	}
`;

const AskButton = () => {
	return (
		<Section>
			<button>
				<a href='/askQuestion'>Ask Question</a>
			</button>
		</Section>
	);
};

export default AskButton;
