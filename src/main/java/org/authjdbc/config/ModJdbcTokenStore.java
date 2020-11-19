/**
 * パッケージ名：org.authjdbc.config
 * ファイル名  ：ModJdbcTokenStore.java
 * 
 * @author mbasa
 * @since Nov 16, 2020
 */
package org.authjdbc.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * 説明：
 *
 */
public class ModJdbcTokenStore extends JdbcTokenStore {

    Logger LOG = LoggerFactory.getLogger(ModJdbcTokenStore.class);
    
    /**
     * コンストラクタ
     *
     * @param dataSource
     */
    public ModJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }

    /* (非 Javadoc)
     * @see org.springframework.security.oauth2.provider.token.store.JdbcTokenStore#readAccessToken(java.lang.String)
     */
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        // TODO 自動生成されたメソッド・スタブ
        OAuth2AccessToken accessToken = null;

        try {
            LOG.debug( tokenValue );
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token "+tokenValue);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " +tokenValue,e);
            removeAccessToken(tokenValue);
        }

        return accessToken;

    }

    
}
