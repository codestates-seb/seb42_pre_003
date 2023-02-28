import React from 'react';
import styled from 'styled-components';

const Section = styled.section`
	button {
		width: auto;
		height: 35px;
		color: #fff;
		background-color: hsl(206deg 100% 52%);
		border-radius: 3px;
		font-size: 13px;
		padding: 9px;
		margin-right: 5px;
	}

	button:hover {
		background-color: hsl(206deg 100% 40%);
	}
`;

const SaveButton = () => {
	return (
		<Section>
			<button>Save profile</button>
		</Section>
	);
};

export default SaveButton;
