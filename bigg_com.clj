(config
(password-field
:name "bearerToken" 
:label "<Provide label here>"))

(default-source
(header-params "Accept" "application/json")
    (paging/page-number
        :page-number-query-param-initial-value 0
        :page-number-query-param-name "page"
        :limit 10 
        :limit-query-param-name "limit" 
    )
                    
(auth/http-bearer)
(http/get :base-url "https://developer.bigcommerce.com/docs/rest-management")
(error-handler 
(when :status 401 :action fail)
)

)



(entity ALL_CHANNEL
    (api-docs-url "https://developer.bigcommerce.com/docs/rest-management/channels#get-all-channels")

    (source (http/get :url "/channels#get-all-channels")
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
    config_meta

)
(dynamic-fields
(flatten-fields
(fields
app

)
:from config_meta)
(flatten-fields
    (fields
    id
    sections
    
    )
    :from "config_meta.app")

    (relate
    (contains-list-of CHANEL_APP_SECTION :inside-prop "sections" ))

    )
)

(entity CHANEL_APP_SECTION
(fields
title
query_path
)
(relate
(needs ALL_CHANNEL :prop "id"))
)


