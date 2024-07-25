(config
(password-field
:name "Api-key" ;;This value is fixed
:label "X-Auth-Token"
:placeholder "Enter your token here"
))

(default-source
(http/get :base-url "https://api.bigcommerce.com/stores/{store_hash}/v3"
(header-params "Accept" "application/json"))
    (paging/page-number
        :page-number-query-param-initial-value 0
        :page-number-query-param-name "page"
        :limit 10 
        :limit-query-param-name "limit" 
    )
                    
(auth/apikey-custom-header :headerName "X-Auth-Token")

(error-handler 
(when :status 401 :action fail)
)

)



(entity CHANNEL
    (api-docs-url "https://developer.bigcommerce.com/docs/rest-management/channels#get-all-channels")

    (source (http/get :url "/channels")
    (extract-path "data"))


    (fields
    id : id
    icon_url
    is_listable_from_ui
    is_visible
    date_created
    external_id
    type
    platform
    date_modified
    name
    status
    config_meta)
(dynamic-fields
(flatten-fields
(flatten-fields
    (fields
    id
    )
    :from "config_meta.app" as config_meta.app ))

)
 (relate
    (contains-list-of CHANEL_APP_SECTION :inside-prop "sections" )))

(entity CHANEL_APP_SECTION
(fields
index :id :index
title
query_path
)
(relate
(needs ALL_CHANNEL :prop "id"))
)


