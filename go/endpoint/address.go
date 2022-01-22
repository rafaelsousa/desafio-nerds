package endpoint

func NewAddressEndpoint() []Endpoint {
	return []Endpoint{
		{
			route:   "/address",
			handler: nil,
		},
	}
}
