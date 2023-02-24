import React from 'react';
import styled from 'styled-components';

const Section = styled.section`
	button {
		width: auto;
		height: 35px;
		color: #39739d;
		background-color: transparent;
		border-radius: 3px;
		font-size: 13px;
		padding: 9px;
	}

	button:hover {
		background-color: hsl(206deg 100% 97%);
	}
`;

const CancelButton = () => {
	return (
		<Section>
			<button>Cancel</button>
		</Section>
	);
};

export default CancelButton;
