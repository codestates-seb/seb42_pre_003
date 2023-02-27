import styled from 'styled-components';
import profile from '../img/profile.png';

const Content = styled.div`
	display: flex;
	flex-grow: 1;
	margin-top: 50px;
`;

const UserBlock = styled.div`
	width: 100%;
	min-height: 500px;
`;

const UserInfo = styled.div`
	display: flex;
	align-items: center;
	padding: 24px;
	border-bottom: 1px solid #e4e6e8;
`;

const UserImage = styled.div`
	width: 96px;
	height: 96px;
	border-radius: 3px;
	overflow: hidden;
	img {
		width: 100%;
		height: 100%;
	}
`;

const UserName = styled.div`
	margin-left: 16px;
	h1 {
		font-size: 34px;
		font-weight: 400;
		color: #232629;
	}
`;

const Container = styled.div`
	display: flex;
	width: 100%;
	height: max-content;
	max-width: 1260px;
	margin: 0 auto;
`;

const ContentContainer = styled.div`
	display: flex;
	width: 80%;
	flex-direction: column;
	padding: 24px;
`;

const ProfileHeaderContainer = styled.div`
	display: flex;
	width: 100%;
	min-height: 144px;
	height: max-content;
	justify-content: space-between;
	margin-bottom: 10px;
`;

const ProfileHeaderButtonContainer = styled.div`
	display: flex;
	width: 245px;
	justify-content: space-between;
`;

const ProfileHeaderButton = styled.button`
	all: unset;
	box-sizing: border-box;
	display: flex;
	align-items: center;
	width: 120px;
	height: 35px;
	padding: 10px;
	background-color: #ffffff;
	border-radius: 3px;
	border: 1px solid #929eaa;
	color: #6a737c;
	font-size: 13px;
	&:hover {
		cursor: pointer;
		background-color: #f7f9f9;
	}
`;

const ProfileBottomContaner = styled.div`
	width: 100%;
	height: max-content;
	display: flex;
	justify-content: space-between;
`;

const BottomRightContainer = styled.div`
	display: flex;
	flex-direction: column;
	width: 70%;
	height: max-content;
	padding: 12px;
`;

const BottomItemContainer = styled.div`
	display: flex;
	flex-direction: column;
	margin-top: -200px;
	margin-bottom: 200px;
`;

const ItemLabel = styled.label`
	display: block;
	font-size: 22px;
	font-weight: 500;
`;

const ItemCard = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	height: 120px;
	padding: 12px;
	border: 1px solid #b5b5b5;
	border-radius: 5px;
	box-sizing: border-box;
`;

const Linker = styled.a`
	color: #0074cc;
	font-size: 15px;
	display: inline;
	&:hover {
		color: #49a5f0;
		cursor: pointer;
	}
`;

const Aboutdescription = styled.span`
	color: #6a737c;
	font-size: 15px;
	display: inline;
`;

const Mypage = () => {
	return (
		<>
			<Container>
				<ContentContainer>
					<ProfileHeaderContainer>
						<Content>
							<UserBlock>
								<UserInfo>
									<UserImage>
										<img src={profile} alt='' />
									</UserImage>
									<UserName></UserName>
								</UserInfo>
							</UserBlock>
						</Content>
						<ProfileHeaderButtonContainer>
							<ProfileHeaderButton>Edit profile</ProfileHeaderButton>
							<ProfileHeaderButton>Delete profile</ProfileHeaderButton>
						</ProfileHeaderButtonContainer>
					</ProfileHeaderContainer>
					<ProfileBottomContaner>
						<BottomRightContainer>
							<BottomItemContainer>
								<ItemLabel>About</ItemLabel>
								<ItemCard
									css={`
										justify-content: center;
										background-color: #f8f9f9;
									`}
								>
									<div
										css={`
											display: inline;
										`}
									>
										<Aboutdescription>
											Your about me section is currently blank. Would you like
											to add one?
										</Aboutdescription>
										<Linker>Edit profile</Linker>
									</div>
								</ItemCard>
							</BottomItemContainer>
							<ItemLabel>Badges</ItemLabel>
							<BottomItemContainer>
								<ItemLabel>Posts</ItemLabel>
							</BottomItemContainer>
						</BottomRightContainer>
					</ProfileBottomContaner>
				</ContentContainer>
			</Container>
		</>
	);
};

export default Mypage;
