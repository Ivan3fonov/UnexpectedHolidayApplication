Rails.application.routes.draw do
  post "/places"=>"places#create"
	get "/places"=>"places#show"
end
